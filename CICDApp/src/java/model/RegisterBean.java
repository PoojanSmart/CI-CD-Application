/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDClient;
import client.UnsecureClient;
import config.UserPasswordHash;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;

/**
 *
 * @author pankti
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean {

    UnsecureClient client;
    Response res;
    String name,email,password,receivesEmail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getReceivesEmail() {
        return receivesEmail;
    }

    public void setReceivesEmail(String receivesEmail) {
        this.receivesEmail = receivesEmail;
    }
    public RegisterBean() {
        client=new UnsecureClient();
    }
    public String registerUser()
    {
        client.registerUser(name, email, new UserPasswordHash().generate(password.toCharArray()), receivesEmail);
        return "index.jsf";
    }
    
}
