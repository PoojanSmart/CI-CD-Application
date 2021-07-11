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
import entity.UserMaster;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pankti
 */
@Named(value = "localRoleUserBean")
@RequestScoped

public class LocalRoleUserBean{

       @EJB FunctionBeanLocal fbl;
        CICDClient client;
        Response res;
        Collection<LocalRolesUser> localRole_users;
        GenericType<Collection<LocalRolesUser>> glocalRole_users;
        Collection<LocalRoles>localRoles;
        GenericType<Collection<LocalRoles>> glocalRoles;
        UserMaster user;
        GenericType<UserMaster>guser;
         
        
        String email,localroleId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocalroleId() {
        return localroleId;
    }

    public void setLocalroleId(String localroleId) {
        this.localroleId = localroleId;
    }
        
        
    public Collection<LocalRolesUser> getLocalRole_users() {
        res=client.getLocalRolesUsers_XML(Response.class);
        localRole_users=res.readEntity(glocalRole_users);
        return localRole_users;
    }

    public void setLocalRole_users(Collection<LocalRolesUser> localRole_users) {
        this.localRole_users = localRole_users;
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

        
        
 
    public LocalRoleUserBean() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();

        client=new CICDClient(token);
        localRole_users=new ArrayList<LocalRolesUser>();
         localRoles=new ArrayList<LocalRoles>();
        glocalRole_users=new GenericType<Collection<LocalRolesUser>>(){};
        glocalRoles=new GenericType<Collection<LocalRoles>>(){};
        guser=new GenericType<UserMaster>(){};
    }
    
    
    public void addLocalRoleUser()
    {
        
      //temporary call   of @EJB direct
        user=fbl.getUserByEmail(email);
        fbl.addLocalRoleUser(Integer.parseInt(localroleId),user.getUserId());
        System.out.println(user.getName());
        email = "";
        localroleId = "";
}
    
    public void removeLocalRoleUser(LocalRolesUser l)
    {
        fbl.removeLocalRoleUser(l.getLocalRolesUserPK().getId(),l.getLocalRoles().getId(),l.getUserMaster().getUserId());
    }

    
//    public LocalRolesUser haveAccess(int userid,int localrole)
}
