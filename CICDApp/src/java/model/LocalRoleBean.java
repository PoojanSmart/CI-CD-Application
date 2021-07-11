/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDClient;
import entity.LocalRoles;
import entity.ProjectMaster;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pankti
 */
@Named(value = "localRoleBean")
@ViewScoped
public class LocalRoleBean implements Serializable{

  CICDClient client;
    Response res;
    Collection<ProjectMaster> projects;
    GenericType<Collection<ProjectMaster>> gprojects;
    Collection<LocalRoles> localRoles;
    GenericType<Collection<LocalRoles>> glocalRoles;
    String role,ProjectId,ProjectCreate,ProjectRemove,ProjectExecute,Build,BuildHistory;
    String c_role,c_ProjectId,c_ProjectCreate,c_ProjectRemove,c_ProjectExecute,c_Build,c_BuildHistory;
    String select_role_id;

    
    
    
    public String getBuildHistory() {
        return BuildHistory;
    }

    public void setBuildHistory(String BuildHistory) {
        this.BuildHistory = BuildHistory;
    }

    
    
    
    
    public Collection<ProjectMaster> getProjects() {
    res=client.getAllProjects_XML(Response.class);
        projects=res.readEntity(gprojects);
      return projects;
    }

    public void setProjects(Collection<ProjectMaster> projects) {
        this.projects = projects;
    }
    


    public Collection<LocalRoles> getLocalRoles() {
   res=client.getLocalRole_XML(Response.class);
   localRoles=res.readEntity(glocalRoles);
    // localRoles=fbl.getLocalRole();
        return localRoles;
    }

    public void setLocalRoles(Collection<LocalRoles> localRoles) {
        this.localRoles = localRoles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getProjectCreate() {
        return ProjectCreate;
    }

    public void setProjectCreate(String ProjectCreate) {
        this.ProjectCreate = ProjectCreate;
    }

    public String getProjectRemove() {
        return ProjectRemove;
    }

    public void setProjectRemove(String ProjectRemove) {
        this.ProjectRemove = ProjectRemove;
    }

    public String getProjectExecute() {
        return ProjectExecute;
    }

    public void setProjectExecute(String ProjectExecute) {
        this.ProjectExecute = ProjectExecute;
    }

    public String getBuild() {
        return Build;
    }

    public void setBuild(String Build) {
        this.Build = Build;
    }

    public String getC_role() {
        return c_role;
    }

    public void setC_role(String c_role) {
        this.c_role = c_role;
    }

    public String getC_ProjectId() {
        return c_ProjectId;
    }

    public void setC_ProjectId(String c_ProjectId) {
        this.c_ProjectId = c_ProjectId;
    }

    public String getC_ProjectCreate() {
        return c_ProjectCreate;
    }

    public void setC_ProjectCreate(String c_ProjectCreate) {
        this.c_ProjectCreate = c_ProjectCreate;
    }

    public String getC_ProjectRemove() {
        return c_ProjectRemove;
    }

    public void setC_ProjectRemove(String c_ProjectRemove) {
        this.c_ProjectRemove = c_ProjectRemove;
    }

    public String getC_ProjectExecute() {
        return c_ProjectExecute;
    }

    public void setC_ProjectExecute(String c_ProjectExecute) {
        this.c_ProjectExecute = c_ProjectExecute;
    }

    public String getC_Build() {
        return c_Build;
    }

    public void setC_Build(String c_Build) {
        this.c_Build = c_Build;
    }

    public String getC_BuildHistory() {
        return c_BuildHistory;
    }

    public void setC_BuildHistory(String c_BuildHistory) {
        this.c_BuildHistory = c_BuildHistory;
    }

    
    

    public String getSelect_role_id() {
        return select_role_id;
    }

    public void setSelect_role_id(String select_role_id) {
        this.select_role_id = select_role_id;
    }
    
    



    public LocalRoleBean() {
           HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();

        client=new CICDClient(token);
     
        glocalRoles=new GenericType<Collection<LocalRoles>>(){};
        gprojects=new GenericType<Collection<ProjectMaster>>(){};
        localRoles=new ArrayList<LocalRoles>();
        projects=new ArrayList<ProjectMaster>();
    }
    
    public void addLocalRole()
    {
        client.addLocalRole(role, ProjectId, ProjectCreate, ProjectRemove, ProjectExecute,Build, BuildHistory);
        role="";
        ProjectId="";
        ProjectCreate="";
        ProjectRemove="";
        ProjectExecute="";
        Build="";
        BuildHistory="";
                
    
    }
    
    public void removeLocalRole(LocalRoles l)
    {
        client.removeLocalRole(l.getId().toString());
    }
    
    
    public void select_role(LocalRoles l)
    {
       
       select_role_id=l.getId().toString();
       c_role=l.getRole();
       c_ProjectId=l.getProjectId().getProjectId().toString();
       c_ProjectCreate=   Boolean.toString(l.getProjectCreate());
       c_ProjectRemove=   Boolean.toString(l.getProjectRemove());
       c_ProjectExecute=   Boolean.toString(l.getProjectExecute());
       c_Build=   Boolean.toString(l.getBuild());
       c_BuildHistory=   Boolean.toString(l.getBuildHistory());
     
       
    }
    
  /*  @PostConstruct
    public void init()
    {
      res=client.getAllProjects_XML(Response.class);
     projects=res.readEntity(gprojects);
     res=client.getLocalRole_XML(Response.class);
     localRoles=res.readEntity(glocalRoles);
    }
    */
    
    public void cancel()
    {
      select_role_id=null;
    }
    public void confirm()
    {
        System.out.println(c_ProjectId);
        client.changeLocalRole(select_role_id,c_role,c_ProjectId,c_ProjectCreate,c_ProjectRemove,c_ProjectExecute, c_Build,c_BuildHistory);
        select_role_id=null;
    }
}