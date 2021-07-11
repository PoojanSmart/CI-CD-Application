/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDClient;
import entity.LocalRoles;
import entity.LocalRolesUser;
import entity.UserMaster;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pankti
 */
@Named(value = "accessBean")
@ViewScoped
public class AccessBean implements  Serializable{

    /**
     * Creates a new instance of AccessBean
     */
    CICDClient client;
    Response res;
    UserMaster loginUser;
    String globalrole;
    Collection<LocalRolesUser> localRolesUsers;
    GenericType<Collection<LocalRolesUser>> glocalRolesUsers;
    String haveLocalRights;

    public String getHaveLocalRights() {
        return haveLocalRights;
    }

    public void setHaveLocalRights(String haveLocalRights) {
        this.haveLocalRights = haveLocalRights;
    }
    
    
    public String getGlobalrole() {
        globalrole=loginUser.getGlobalRoleId().getRole();
        return globalrole;
    }

    public void setGlobalrole(String globalrole) {
        this.globalrole = globalrole;
    }
    
    
    
    
    public UserMaster getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(UserMaster loginUser) {
        this.loginUser = loginUser;
    }
    
    
    public boolean getHaveBuildRightsForProject(int projectId)
    {
        LocalRoles lr =  new LocalRoles();
        GenericType<LocalRoles> glr = new GenericType<LocalRoles>(){};
        res = client.getLocalRoleByProjectAndUser_XML(Response.class, Integer.toString(projectId),Integer.toString(loginUser.getUserId()));
        lr = res.readEntity(glr);
        if(lr.getBuild())
            return true;
        return false;
    }
    
    public boolean getHaveBuildHistoryViewRights(int projectId)
    {
        LocalRoles lr =  new LocalRoles();
        GenericType<LocalRoles> glr = new GenericType<LocalRoles>(){};
        res = client.getLocalRoleByProjectAndUser_XML(Response.class, Integer.toString(projectId),Integer.toString(loginUser.getUserId()));
        lr = res.readEntity(glr);
        if(lr.getBuildHistory())
            return true;
        return false;
    }
    
    public boolean getHaveProjectRemoveRights(int projectId)
    {
        LocalRoles lr =  new LocalRoles();
        GenericType<LocalRoles> glr = new GenericType<LocalRoles>(){};
        res = client.getLocalRoleByProjectAndUser_XML(Response.class, Integer.toString(projectId),Integer.toString(loginUser.getUserId()));
        lr = res.readEntity(glr);
        if(lr.getProjectRemove())
            return true;
        return false;
    }
    
    public boolean getHaveProjectModifyRights(int projectId)
    {
        LocalRoles lr =  new LocalRoles();
        GenericType<LocalRoles> glr = new GenericType<LocalRoles>(){};
        res = client.getLocalRoleByProjectAndUser_XML(Response.class, Integer.toString(projectId),Integer.toString(loginUser.getUserId()));
        lr = res.readEntity(glr);
        if(lr.getProjectCreate())
            return true;
        return false;
    }
    
    public AccessBean() {
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();
        client=new CICDClient(token);
         FacesContext context=FacesContext.getCurrentInstance();
              HttpSession session=(HttpSession) context.getExternalContext().getSession(true);
              loginUser=(UserMaster) session.getAttribute("loginUser");
              localRolesUsers=new ArrayList<LocalRolesUser>();
              glocalRolesUsers=new GenericType<Collection<LocalRolesUser>>(){};
              
        
        
    }
    
    
    
}
