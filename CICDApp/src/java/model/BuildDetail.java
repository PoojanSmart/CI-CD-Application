/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.CICDClient;
import entity.BuildMaster;
import entity.ProjectMaster;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author smart
 */
@Named(value = "buildDetail")
@RequestScoped
public class BuildDetail {
    
    BuildMaster build;
    CICDClient client;
    Response res;
    GenericType<BuildMaster> gb;
    String buildId;
    public BuildDetail() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();
        buildId= FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("buildId");
        
        client = new CICDClient(token);
        gb = new GenericType<BuildMaster>(){};
    }

    public BuildMaster getBuild()
    {
        res = client.getBuild_XML(Response.class, buildId);
        build=res.readEntity(gb);
        return build;
    }

}
