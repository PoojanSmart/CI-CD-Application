/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDClient;
import entity.GlobalRoles;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pankti
 */
@Named(value = "globalRoleBean")
@ViewScoped
public class GlobalRoleBean implements Serializable{
String id, role,ProjectCreate,ProjectRemove,ProjectExecute,Build,BuildHistory,CredentialView,CredentialCreate,CredentialDelete;
String select_role_id=null;
GlobalRoles globalRole;
String change_id;
CICDClient client;
Response res;
Collection<GlobalRoles> globalRoles;
GenericType<Collection<GlobalRoles>> gglobalRoles;

String c_role,c_ProjectCreate,c_ProjectRemove,c_ProjectExecute,c_Build,c_BuildHistory,c_CredentialView,c_CredentialCreate,c_CredentialDelete;

    public String getC_role() {
        return c_role;
    }

    public void setC_role(String c_role) {
        this.c_role = c_role;
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

    public String getC_CredentialView() {
        return c_CredentialView;
    }

    public void setC_CredentialView(String c_CredentialView) {
        this.c_CredentialView = c_CredentialView;
    }

    public String getC_CredentialCreate() {
        return c_CredentialCreate;
    }

    public void setC_CredentialCreate(String c_CredentialCreate) {
        this.c_CredentialCreate = c_CredentialCreate;
    }

    public String getC_CredentialDelete() {
        return c_CredentialDelete;
    }

    public void setC_CredentialDelete(String c_CredentialDelete) {
        this.c_CredentialDelete = c_CredentialDelete;
    }




    public GlobalRoles getGlobalRole() {
        return globalRole;
    }

    public void setGlobalRole(GlobalRoles globalRole) {
        this.globalRole = globalRole;
    }




    public String getSelect_role_id() {
        return select_role_id;
    }

    public void setSelect_role_id(String select_role_id) {
        this.select_role_id = select_role_id;
    }



   
    public String getCredentialDelete() {
        return CredentialDelete;
    }

    public void setCredentialDelete(String CredentialDelete) {
        this.CredentialDelete = CredentialDelete;
    }
 
    
    
    
    public HtmlDataTable getDataTableGlobalRoles() {
        return dataTableGlobalRoles;
    }

    public void setDataTableGlobalRoles(HtmlDataTable dataTableGlobalRoles) {
        this.dataTableGlobalRoles = dataTableGlobalRoles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getBuildHistory() {
        return BuildHistory;
    }

    public void setBuildHistory(String BuildHistory) {
        this.BuildHistory = BuildHistory;
    }

    public String getCredentialView() {
        return CredentialView;
    }

    public void setCredentialView(String CredentialView) {
        this.CredentialView = CredentialView;
    }

    public String getCredentialCreate() {
        return CredentialCreate;
    }

    public void setCredentialCreate(String CredentialCreate) {
        this.CredentialCreate = CredentialCreate;
    }

    public Collection<GlobalRoles> getGlobalRoles() {
        res=client.getAllGlobalRoles_XML(Response.class);
        globalRoles=res.readEntity(gglobalRoles);
        return globalRoles;
    }

    public void setGlobalRoles(Collection<GlobalRoles> globalRoles) {
        this.globalRoles = globalRoles;
    }
HtmlDataTable dataTableGlobalRoles;

    public String getChange_id() {
        return change_id;
    }

    public void setChange_id(String change_id) {
        this.change_id = change_id;
    }
    
    
    
    public GlobalRoleBean() {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();

        client=new CICDClient(token);
     
        globalRoles=new ArrayList<GlobalRoles>();
        gglobalRoles=new GenericType<Collection<GlobalRoles>>(){};
                
    }
    
      
      
      
    
           
    public void addGlobalRole()
    {
     //   System.out.println("Saye Hello add");
        client.addGlobalRole(role, ProjectCreate, ProjectRemove, ProjectExecute, Build, BuildHistory, CredentialView, CredentialCreate, CredentialDelete);
        role="";
        ProjectCreate="";
        ProjectExecute="";
        ProjectRemove="";
        Build="";
        BuildHistory="";
        CredentialView="";
        CredentialCreate="";
        CredentialDelete="";
    }
    
    public void removeGlobalRole(GlobalRoles g)
    {
        client.removeGlobalRole(g.getId().toString());
    }
    
    public void select_role(GlobalRoles g)
    {
        select_role_id=g.getId().toString();
        c_role=g.getRole();
        c_ProjectCreate=Boolean.toString(g.getProjectCreate());
        c_ProjectExecute=Boolean.toString(g.getProjectExecute());
        c_ProjectRemove=Boolean.toString(g.getProjectRemove());
        c_Build=Boolean.toString(g.getBulid());
        c_BuildHistory=Boolean.toString(g.getBuildHistory());
        c_CredentialCreate=Boolean.toString(g.getCredentialCreate());
        c_CredentialDelete=Boolean.toString(g.getCredentialDelete());
        c_CredentialView=Boolean.toString(g.getCredentialView());
        
        this.globalRole=g;
        
    }
    public void confirm()
    {
        client.changeGlobalRole(select_role_id, c_role,c_ProjectCreate,c_ProjectRemove, c_ProjectExecute,c_Build, c_BuildHistory, c_CredentialView,c_CredentialCreate, c_CredentialDelete);
        select_role_id=null;
        //System.out.println("Say Hello:"+c_role);
    }
    
    public void cancel()
    {
        select_role_id=null;
    }
}