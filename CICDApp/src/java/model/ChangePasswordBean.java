/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.UnsecureClient;
import config.UserPasswordHash;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author pankti
 */
@Named(value = "changePasswordBean")
@ViewScoped
public class ChangePasswordBean implements  Serializable {

    String email,new_password,old_password;
    UnsecureClient client;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }
    
    public ChangePasswordBean() {
        client=new UnsecureClient();
    }
    
    public String changePassword()
    {
      String str=  client.changePassword(email, new UserPasswordHash().generate(old_password.toCharArray()), new UserPasswordHash().generate(new_password.toCharArray()));
      if(str.equals("Successfully"))
      {
          return "./home.jsf";
      }
      else
      {
          return "/changePassword.jsf";
      }
    
    }
    
}
