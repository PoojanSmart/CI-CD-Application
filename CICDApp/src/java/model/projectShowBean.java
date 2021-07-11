/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.FunctionBeanLocal;
import client.CICDClient;
import entity.LocalRoles;
import entity.LocalRolesUser;
import entity.ProjectMaster;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author smart
 */
@Named(value = "projectShowBean")
@SessionScoped
public class projectShowBean implements Serializable {

    CICDClient client;
    Response res;
    ProjectMaster project;
    GenericType<ProjectMaster> gproject;
    
    Collection<LocalRolesUser> lruc;
    GenericType<Collection<LocalRolesUser>> glruc;
    
    Collection<LocalRoles> lrc;
    GenericType<Collection<LocalRoles>> glrc;

    public Collection<LocalRolesUser> getLruc() {
        Collection<LocalRolesUser> local = new ArrayList<LocalRolesUser>();

        res = client.getLocalRoleByProject_XML(Response.class, project.getProjectId().toString());
        lrc = res.readEntity(glrc);
        
        res = client.getLocalRolesUsers_XML(Response.class);
        lruc = res.readEntity(glruc);
        
        for (LocalRolesUser k :lruc)
        {
            if(lrc.contains(k.getLocalRoles()))
            {
                local.add(k);
            }
        }
        return local;
        
    }
    
    
    String projectId;
    
    public projectShowBean() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();
        client = new CICDClient(token);
        
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("projectId") != null)
            projectId =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("projectId");
        
        gproject = new GenericType<ProjectMaster>(){};
        res = client.getProject_XML(Response.class, projectId);
        project = res.readEntity(gproject);
        
        glruc = new GenericType<Collection<LocalRolesUser>>(){};
        glrc = new GenericType<Collection<LocalRoles>> (){};
        }

    public ProjectMaster getProject() {
        return project;
    }

    public void setProject(ProjectMaster project) {
        this.project = project;
    }
    
    public void applyChanges()
    {
       if(this.project.getRepoId().getGitId() != null)
           client.updateProjectWithGitUrl(project.getProjectId().toString(), 
                   project.getProjectName(), 
                   project.getShellCommand(),
                   project.getTimeInterval(),
                   project.getRepoId().getPysicalPath(), 
                   project.getRepoId().getGitId().getRepoUrl());
       else if(this.project.getRepoId() != null)
           client.updateProjectWithPhysicalpath(project.getProjectId().toString(), 
                   project.getProjectName(), 
                   project.getShellCommand(),
                   project.getTimeInterval(),
                   project.getRepoId().getPysicalPath());
       else
           client.updateProject(project.getProjectId().toString(),
                   project.getProjectName(), 
                   project.getShellCommand(),
                   project.getTimeInterval());
       
    }
    
}
