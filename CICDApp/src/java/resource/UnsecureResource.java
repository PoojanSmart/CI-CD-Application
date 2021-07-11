/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import bean.FunctionBeanLocal;
import entity.BuildMaster;
import entity.UserMaster;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author smart
 */
@Path("notsecure")
@RequestScoped
public class UnsecureResource {

    @EJB FunctionBeanLocal fbl;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UnsecureResource
     */
    public UnsecureResource() {
    }

  
    
    @POST
    @Path("registerUser/{name}/{email}/{password}/{receivesEmail}")
    public void registerUser(@PathParam("name") String name,@PathParam("email") String email,@PathParam("password") String password
            ,@PathParam("receivesEmail") Boolean receivesEmail) {
    fbl.registerUser(name, email, password, receivesEmail);
    }
    
    @POST
    @Path("registerUserWithRole/{name}/{email}/{password}/{receivesEmail}/{golbalRoleId}")
  
    public void registerUserWithRole(@PathParam("name") String name,@PathParam("email") String email,@PathParam("password") String password
            ,@PathParam("receivesEmail") Boolean receivesEmail,@PathParam("golbalRoleId") Integer golbalRoleId) {
      
        fbl.registerUser(name, email,password, receivesEmail, golbalRoleId);
    }
    
    
    @POST
    @Path("loginUser/{email}/{password}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public UserMaster loginUser(@PathParam("email")String email,@PathParam("password")String password) {
        UserMaster user=fbl.loginUser(email, password);
        return user;
    }

    @POST
    @Path("changePassword/{email}/{old_password}/{new_password}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public String changePassword(@PathParam("email")String email,@PathParam("old_password") String old_password,@PathParam("new_password") String new_password) {
     String change_pwd=fbl.changePassword(email, old_password, new_password);
     return change_pwd;
    
    }
    
}
