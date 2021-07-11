/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDTextClient;
import config.UserPasswordHash;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pankti
 */
@Named(value = "resetPwdBean")
@ApplicationScoped
public class ResetPwdBean {

    String email,password,confirm_password;

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
    
    CICDTextClient client;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public ResetPwdBean() {
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
    
    public String resetpassword()
    {
        String result=client.resetpassword(email, new UserPasswordHash().generate(password.toCharArray()));
        FacesContext context=FacesContext.getCurrentInstance();
        HttpSession session=(HttpSession) context.getExternalContext().getSession(true);
       // System.out.println(result);
       if(result.equals("1"))
       {
             session.removeAttribute("email");
      
           return "index.jsf";
       }
       else
       {
           return "resetpassword.jsf";
       }
    }
    
}
