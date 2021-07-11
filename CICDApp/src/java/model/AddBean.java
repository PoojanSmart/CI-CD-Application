/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDClient;
import entity.RepoCredential;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jgit.api.errors.GitAPIException;
import test.GitOps;

/**
 *
 * @author pankti
 */
@Named(value = "addBean")
@ViewScoped
public class AddBean implements Serializable{

    CICDClient client;
    
    String projectname;
    String shellcommand;
    String timeinterval;
    String physicalpath;
    String gitUrl;
    String gitusername;
    String gitpassword;
    String showPhysicalPath="none";
    String showGitUrl="none";
    String showGitCred="none";

    Boolean isPhysicalPath;
    Boolean isGiturl;
    Boolean isGitCredential;
    Boolean isGitUrlValid = false;

    String msg;
    
    
    
    public Boolean getIsGitUrlValid() {
        return isGitUrlValid;
    }

    public void setIsGitUrlValid(Boolean isGitUrlValid) {
        this.isGitUrlValid = isGitUrlValid;
    }

    public String getShowPhysicalPath() {
        return showPhysicalPath;
    }
    public Boolean getIsPhysicalPath() {
        return isPhysicalPath;
    }

    public void setIsPhysicalPath(Boolean isPhysicalPath) {
        this.isPhysicalPath = isPhysicalPath;
    }

    public Boolean getIsGiturl() {
        return isGiturl;
    }

    public void setIsGiturl(Boolean isGiturl) {
        this.isGiturl = isGiturl;
    }

    public Boolean getIsGitCredential() {
        return isGitCredential;
    }

    public void setIsGitCredential(Boolean isGitCredential) {
        this.isGitCredential = isGitCredential;
    }
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getShellcommand() {
        return shellcommand;
    }

    public void setShellcommand(String shellcommand) {
        this.shellcommand = shellcommand;
    }

    public String getTimeinterval() {
        return timeinterval;
    }

    public void setTimeinterval(String timeinterval) {
        this.timeinterval = timeinterval;
    }

    public String getPhysicalpath() {
        return physicalpath;
    }

    public void setPhysicalpath(String physicalpath) {
        this.physicalpath = physicalpath;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getGitusername() {
        return gitusername;
    }

    public void setGitusername(String gitusername) {
        this.gitusername = gitusername;
    }

    public String getGitpassword() {
        return gitpassword;
    }

    public void setGitpassword(String gitpassword) {
        this.gitpassword = gitpassword;
    }
    public AddBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();

        client=new CICDClient(token);
    }
    
    public void togglePhysicalPath()
    {
        if(showPhysicalPath.equals("none"))
            showPhysicalPath = "block";
        else
            showPhysicalPath = "none";
    }
    
    public void toggleGitCred()
    {
        if(showGitCred.equals("none"))
            showGitCred = "block";
        else
            showGitCred = "none";
    }

    public String getShowGitUrl() {
        return showGitUrl;
    }

    public String getShowGitCred() {
        return showGitCred;
    }
    
    public void toggleGitUrl()
    {
        if(showGitUrl.equals("none"))
            showGitUrl = "block";
        else
            showGitUrl = "none";
    }
    
    public void addProject()
    {
        
        
        
      try{
             if(isPhysicalPath)
             {
                 if(physicalpath.isEmpty())
                 {
                                 FacesContext.getCurrentInstance().addMessage("myform:physicalpath",new FacesMessage("Physical path is required"));
                     
                 }
             }
            if(projectname.isEmpty())
            {
                         FacesContext.getCurrentInstance().addMessage("myform:projectname",new FacesMessage("Project name is required"));
          
            }
            if(timeinterval.isEmpty())
            {
                         FacesContext.getCurrentInstance().addMessage("myform:timeinterval",new FacesMessage("Time interval is required"));
          
            }
            if(shellcommand.isEmpty())
            {
                         FacesContext.getCurrentInstance().addMessage("myform:shellcommand",new FacesMessage("Shell command is required"));
          
            }
          
            if(isGiturl.booleanValue())
            {
               if(gitUrl.isEmpty()) 
               {
                   FacesContext.getCurrentInstance().addMessage("myform:gitUrl",new FacesMessage("Git Url is required"));
               }
                
                if(isGitCredential.booleanValue())
                {
                    
                    if(gitusername.isEmpty())
                    {
                                 FacesContext.getCurrentInstance().addMessage("myform:gitusername",new FacesMessage("Git username is required"));
          
                    }
                    if(gitpassword.isEmpty())
                    {
                            FacesContext.getCurrentInstance().addMessage("myform:gitpassword",new FacesMessage("Git password is required"));
          
                    }
                        
                    if(!GitOps.validateRepo(this.gitUrl,new RepoCredential(this.gitusername,this.gitpassword)))
                    {
                        //FacesContext.getCurrentInstance().addMessage(null, 
                        //new FacesMessage(FacesMessage.SEVERITY_ERROR, "Git Repository is not Valid","Git Repository is not Valid"));                    
                             FacesContext.getCurrentInstance().addMessage("myform:gitpassword",new FacesMessage("Git Repository is not Valid"));
          
                    }
                }
                else
                {
                    if(!GitOps.validateRepo(this.gitUrl))
                    {
                 //       FacesContext.getCurrentInstance().addMessage(null, 
                   //     new FacesMessage(FacesMessage.SEVERITY_ERROR, "Git Repository is not Valid","Git Repository is not Valid"));
                                  FacesContext.getCurrentInstance().addMessage("myform:gitUrl",new FacesMessage("Git Repository is not Valid"));
          
                    }
                }
            }
      }
      catch(Exception r){
          //FacesContext.getCurrentInstance().addMessage(null, 
           // new FacesMessage(FacesMessage.SEVERITY_ERROR, "Git Repository is not Valid","Git Repository is not Valid"));
                  FacesContext.getCurrentInstance().addMessage("myform:gitUrl",new FacesMessage("Git Repository is not Valid"));
          
           return;
      }
      
      if(!isPhysicalPath.booleanValue())
          
          client.createProject(projectname, shellcommand, timeinterval);
      
      else
          
      {
          if(!isGiturl.booleanValue())
             client.createProjectWithPhysicalpath(projectname, shellcommand, timeinterval, physicalpath);    
          else
          {
              if(!isGitCredential.booleanValue())
                client.createProjectWithGitUrl(projectname, shellcommand, timeinterval, physicalpath, gitUrl);
              
              else
                  client.createProjectWithGitCredential(projectname, shellcommand, timeinterval, physicalpath,gitUrl, gitusername, gitpassword);
              
          }
      }
      projectname="";
      shellcommand="";
      timeinterval="";
      isGiturl=false;
      isPhysicalPath=false;
      isGitCredential=false;
      physicalpath="";
      gitUrl="";
      gitpassword="";
      gitusername="";
      
     showPhysicalPath="none";
     showGitUrl="none";
     showGitCred="none";
      
    }
    public void changeBooleanPhysical()
    {
        System.out.println("Call");
        if(isPhysicalPath.booleanValue()==false)
        {
            isPhysicalPath=true;
        }
        else
        {
            isPhysicalPath=false;
        }
    }
    
    
    
    
}
