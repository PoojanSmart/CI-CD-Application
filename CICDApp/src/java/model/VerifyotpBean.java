/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDTextClient;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pankti
 */
@Named(value = "verifyOTPBean")
@ApplicationScoped
public class VerifyotpBean {

    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    String otp;
    CICDTextClient client;
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
    
    
    
    
    public VerifyotpBean(){
        client=new CICDTextClient();
       
          
          try{
              FacesContext context=FacesContext.getCurrentInstance();
              HttpSession session=(HttpSession) context.getExternalContext().getSession(true);
              email=(String) session.getAttribute("email");
          }catch(Exception ex)
          {
              System.err.println(ex.getMessage());
          }
    
    }
    
    public String verifyOTP()
    {
           
          String result=client.verifyOTP(email, otp);
          //System.out.println(result);
          
          
          if(result.equals("OTPSuccess"))
          {
              return "resetpassword.jsf";
          }
          else if(result.equals("TimeOut"))
          {
              
              return "verifyotp.jsf";
          }
          else
          {
              return "verifyotp.jsf";
          }
          
    }
    
}
