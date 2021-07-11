/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDClient;
import entity.RepoCredential;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
@Named(value = "repoCredentialBean")
@ViewScoped
public class RepoCredentialBean implements Serializable{

    CICDClient client;
    Response res;
    Collection<RepoCredential> repoCredential;
    GenericType<Collection<RepoCredential>> grepoCredential;

    public Collection<RepoCredential> getRepoCredential() {
        res=client.getAllRepoCredentials_XML(Response.class);
        repoCredential=res.readEntity(grepoCredential);
        return repoCredential;
    }

    public void setRepoCredential(Collection<RepoCredential> repoCredential) {
        this.repoCredential = repoCredential;
    }
    
    
    
    
    
    public RepoCredentialBean() {
               HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();
        client=new CICDClient(token);
        grepoCredential=new  GenericType<Collection<RepoCredential>>(){};
        repoCredential=new ArrayList<RepoCredential>();
     
    }
    
    public void remove(Integer userid,Integer credentialId)
    {
        client.removeRepoCredentialOfUser(userid.toString(),credentialId.toString());
    }
    
    
}
