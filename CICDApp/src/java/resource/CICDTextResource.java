/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import bean.FunctionBeanLocal;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("cicd1")
@RequestScoped
public class CICDTextResource {

   @EJB FunctionBeanLocal fbl;
    @Context
    private UriInfo context;

    public CICDTextResource() {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("forgotpassword/{email}")
    public String forgotpassword(@PathParam("email") String email) {
     String result=fbl.forgotpassword(email);
     return result;
     
    }
    
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("verifyOTP/{email}/{otp}")
    public String verifyOTP(@PathParam("email")String email,
            @PathParam("otp")String otp)
    {
        String result=fbl.verifyOTP(email, otp);
        return result;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("resetpassword/{email}/{password}")
    public String resetpassword(@PathParam("email")String email,@PathParam("password")String password)
    {
        String result=fbl.resetPassword(email, password);
        return result;
    }
    
    
    

    
    
}
