/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.FunctionBeanLocal;
import client.CICDClient;
import entity.GlobalRoles;
import entity.UserMaster;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
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
@Named(value = "userBean")
@ViewScoped
public class UserBean implements Serializable{

    CICDClient client;
    Response res;
    String UserId,Name,Email,Password,ReceivesEmail,GlobalId,GlobalRole;
    @EJB FunctionBeanLocal fbl;
    GlobalRoles Role;
    Collection<GlobalRoles> globalRoleses;
    GenericType<Collection<GlobalRoles>> gglobalRoleses;
    
    
    Collection<UserMaster> users;
    GenericType<Collection<UserMaster>> gusers;
    int select_role;
    String select_id;

  
    
    
    
    
    public String getGlobalRole() {
        return GlobalRole;
    }

    public void setGlobalRole(String GlobalRole) {
        this.GlobalRole = GlobalRole;
    }

    
    
    
    public String getSelect_id() {
        return select_id;
    }

    public void setSelect_id(String select_id) {
        this.select_id = select_id;
    }
    
    
    
    

    public int getSelect_role() {
        return select_role;
    }

    public void setSelect_role(int select_role) {
        this.select_role = select_role;
    }
    
   
   
    
    
       
    
    public String getGlobalId() {
        return GlobalId;
    }

    
    
    public void setGlobalId(String GlobalId) {
        this.GlobalId = GlobalId;
    }
    
    
    
    

    public Collection<GlobalRoles> getGlobalRoleses() {
        
        res=client.getAllGlobalRoles_XML(Response.class);
        globalRoleses=fbl.getAllGlobalRoles();
        return globalRoleses;
    }

    public void setGlobalRoleses(Collection<GlobalRoles> globalRoleses) {
        this.globalRoleses=globalRoleses;
    }
    
    public UserBean() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();

        client=new CICDClient(token);
        globalRoleses=new ArrayList<GlobalRoles>();
        gglobalRoleses=new GenericType<Collection<GlobalRoles>>(){};
        users=new ArrayList<UserMaster>();
        gusers=new GenericType<Collection<UserMaster>>(){};
    }



    

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getReceivesEmail() {
        return ReceivesEmail;
    }

    public void setReceivesEmail(String ReceivesEmail) {
        this.ReceivesEmail = ReceivesEmail;
    }


    public void setRole(GlobalRoles Role) {
        this.Role = Role;
    }

   

    public Collection<UserMaster> getUsers() {
        res=client.getAllUsers_XML(Response.class);
        users=fbl.getAllUsers();
        return users;
    }

    public void setUsers(Collection<UserMaster> users) {
        this.users = users;
    }
//    
//    public void addUser()
//    {
//        Password=Email;
//        client.registerUserWithRole(Name, Email,Password, ReceivesEmail, GlobalId);
//        Name="";
//        Email="";
//        Password="";
//        ReceivesEmail="";
//        GlobalId="";
//        
//    }
//    
    public void select_user(UserMaster u)
    {
        select_id=u.getUserId().toString();
        Name=u.getName();
        Email=u.getEmail();
        ReceivesEmail=Boolean.toString(u.getReceivesEmail());
        GlobalId=u.getGlobalRoleId().getId().toString();
        //GlobalRole=u.getGlobalRoleId().getRole();*/
        
       
    }
    
    public void confirm()
    {
        System.out.println(Name);
        client.updateUser(select_id, Name, Email, ReceivesEmail, GlobalId);
        select_id=null;
    }
    
    public void cancel()
    {
        select_id=null;
    }
    
    public void removeUser(UserMaster u)
    {
        client.removeUser(u.getUserId().toString());
    }
    
}
