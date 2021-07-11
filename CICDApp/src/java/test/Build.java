/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entity.BuildMaster;
import entity.ProjectMaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author smart
 */
@Stateless
@LocalBean
public class Build 
{
    @PersistenceContext(name ="mypu")
    EntityManager em;
    
    public ProjectMaster pm;
    
    public Build() {}
    
    public Build(ProjectMaster pm)
    {
        this.pm = pm;
    }
    
    public Build setProject(ProjectMaster pm)
    {
        this.pm = pm;
        return this;
    }
    
    public ArrayList runShell(TreeMap<String,ArrayList<String>> commandsDict,String context) throws IOException
    {
        
        boolean status=true;
        String endStage="PreStart";
        String shellOutput="";
        
        for(String stage:commandsDict.keySet())
        {
                
                endStage = stage;
                ArrayList<String> commands = commandsDict.get(stage);
                ArrayList shellRet = commandExecutor(commands, context);
                
                shellOutput += (String)shellRet.get(1);
                        
                if((boolean)shellRet.get(0))
                    status = true;
                else
                {status = false;break;}
        }
        ArrayList returnValues = new ArrayList();
        
        returnValues.add(status);
        returnValues.add(endStage);
        returnValues.add(shellOutput);

        
        return returnValues;
    }
    
    public void BuildSequance() throws GitAPIException, IOException
    {
        GitOps.pollSCM(pm.getRepoId());               //polling SCM to get/update project files
        ArrayList shellValues = runShell(Parser.commandParser(pm.getShellCommand()),pm.getRepoId().getPysicalPath());    
        insertBuild((boolean)shellValues.get(0),(String)shellValues.get(1),(String)shellValues.get(2));

    }
    
    public ArrayList commandExecutor(ArrayList<String> commands,String context) throws IOException
    {
        boolean status = false;
        String output="",s="";
        ArrayList returnValues = new ArrayList();
                
        for(String command:commands)
        {
            ArrayList<String> cmds = new ArrayList<String>();
            cmds.add("bash");
            cmds.add("-c");
            cmds.add(command+";"+"if [ $? -eq 0 ]; then echo RUNOK;else echo RUNFAIL;fi");
            ProcessBuilder pb = new ProcessBuilder(cmds);
            pb.directory(new File(context));
            Process p = pb.start();



            InputStream is = (p.getInputStream());
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(isr);

            while((s = bf.readLine())!=null)
            {
                output +=s;
            }

            is = (p.getErrorStream());
            isr = new InputStreamReader(is);
            bf = new BufferedReader(isr);

            while((s = bf.readLine())!=null)
            {
                output +=s;
            }

            status = isCommandExecuted(output);
            if(!status)
            {
                output = output.replace("RUNFAIL", "");
                break;
            }
            else
                output = output.replace("RUNOK", "");
            
        }
        
        
        returnValues.add(status);
        returnValues.add(output);

        return returnValues;
    }
    
    public void insertBuild(boolean status, String endStage, String shellOutput)
    {
        BuildMaster build = new BuildMaster(shellOutput,endStage,status);
        if(pm!=null)
        {
            ProjectMaster p = em.find(ProjectMaster.class, pm.getProjectId());
            build.setProjectId(p);
        }
        em.persist(build);
        

    }
    
    public boolean isCommandExecuted(String output) throws IOException
    {
        boolean status = false;
        if(output.contains("RUNOK"))
            status = true;
        else if(output.contains("RUNFAIL"))
            status =  false; 
        return status;
        
    }
    
    
    
}
