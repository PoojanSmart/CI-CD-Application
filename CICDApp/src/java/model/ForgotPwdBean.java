/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDTextClient;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pankti
 */
@Named(value = "forgotPwdBean")
@RequestScoped
public class ForgotPwdBean {

    
    String email;
    CICDTextClient client;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public ForgotPwdBean(){
        client=new CICDTextClient();
    }
    public String forgotpassword()
    {
        String result=client.forgotpassword(email);
        System.out.println(result);
        if(result.equals("1"))
        {
            FacesContext context = FacesContext.getCurrentInstance();
           /* HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            request.setAttribute("email", this.email);
            */
            HttpSession session=(HttpSession) context.getExternalContext().getSession(true);
           session.setAttribute("email", this.email);
            return "verifyotp.jsf";
        }
        else
        {
            return "forgotpassword.jsf";
        }
              
    }

    
}
