/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.FunctionBeanLocal;
import client.CICDClient;
import entity.ProjectMaster;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pankti
 */
@Named(value = "showBean")
@RequestScoped
public class ShowBean implements Serializable{

    CICDClient client;
    Response res;
    Collection<ProjectMaster> project;

    GenericType<Collection<ProjectMaster>> gproject;
    ProjectMaster projectMaster;
    GenericType<ProjectMaster> gprojectMaster;
    @EJB FunctionBeanLocal fbl;


    
    public ShowBean() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();

        client=new CICDClient(token);
        project=new ArrayList<ProjectMaster>();
        gproject=new GenericType<Collection<ProjectMaster>>(){};
        gprojectMaster=new GenericType<ProjectMaster>(){};
    }
    
    public Collection<ProjectMaster> getProject()
    {
        //res=client.getAllProjects_JSON(Response.class);
       res=client.getAllProjects_XML(Response.class);
      //  System.err.println(res);
        project=res.readEntity(gproject);
        
       // System.out.println(project);
        return project;
    }
    public void Build(Integer projectId)
    {
        try{client.buildProject(Integer.toString(projectId));}
        catch(Exception e){
            FacesMessage facesMessage = new FacesMessage("Error occured while building");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return;
        }
        FacesMessage facesMessage = new FacesMessage("Last Build was Successful");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        return;
    }
    
    public void goToBuildPage(String projectId) throws IOException
    {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../build.jsf?projectId="+projectId);
    }
  
    public ProjectMaster checkLocalRights(Integer userid,Integer pId)
    {
        res=client.checkLocalRights_XML(Response.class, userid.toString(), pId.toString());
        projectMaster=res.readEntity(gprojectMaster);
        return projectMaster;
    }
    
    public boolean getStateOfProjectTimer(String projectName)
    {
//        Boolean b = false;
//        GenericType<Boolean> gb = new GenericType<Boolean>(){};
//        res = client.getTimerState_XML(Response.class, projectName);
//        b = res.readEntity(gb);
        
        return fbl.getTimerState(projectName);
    }
    
    public void stopTimer(String projectName)
    {
        client.stopTimer(projectName);
    }
    public void startTimer(String projectName)
    {
        client.startTimer(projectName);
    }
    
    public void removeProject(String projectId)
    {
        System.out.println("Delete");
        fbl.removeProject(Integer.parseInt(projectId));
    }
    
}
