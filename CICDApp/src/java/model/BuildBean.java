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
import java.util.Map;
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
@Named(value = "buildBean")
@RequestScoped
public class BuildBean {
    
    String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    Integer currentPage = 0;
    Integer maxRecordsPerPage = 7;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getMaxRecordsPerPage() {
        return maxRecordsPerPage;
    }

    public void setMaxRecordsPerPage(Integer maxRecordsPerPage) {
        this.maxRecordsPerPage = maxRecordsPerPage;
    }
    public boolean getNext()
    {
        if((currentPage+1)*maxRecordsPerPage<builds.size())
            return true;
        return false; 
    }
    
    public boolean getPrev()
    {
        if(currentPage>0)
            return true;
        return false; 
    }
    
    public void donext()
    {
        currentPage++;
    }

    public void doprev()
    {
        currentPage--;
    }
    
    CICDClient client;
    Response res;
    Collection<BuildMaster> builds;
    GenericType<Collection<BuildMaster>> gbuilds;
    
    public BuildBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        String token = request.getSession().getAttribute("token").toString();

        client = new CICDClient(token);
        builds = new ArrayList<BuildMaster>();
        gbuilds = new GenericType<Collection<BuildMaster>>(){};
        
        projectId =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("projectId");        
        if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page") != null)
            currentPage =  Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("page"));

        
        res = client.getBuildByProject_XML(Response.class, projectId);

        builds = res.readEntity(gbuilds);
    }
    

    public Collection<BuildMaster> getBuilds() {

        Collection<BuildMaster> restricted = new ArrayList<BuildMaster>();
        
        for(int i=0;i<maxRecordsPerPage;i++)
        {
            if(builds.size()>(currentPage * maxRecordsPerPage) + i )
            {
                Object a[] = builds.toArray();
                restricted.add((BuildMaster)a[(currentPage * maxRecordsPerPage) + i]);
            }
        }

        return restricted;

    }

    public void setBuilds(Collection<BuildMaster> builds) {
        this.builds = builds;
    }
}

