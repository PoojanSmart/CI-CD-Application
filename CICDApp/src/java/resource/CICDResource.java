/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import static auth.Constants.AUTHORIZATION_HEADER;
import auth.JWTCredential;
import auth.TokenProvider;
import bean.FunctionBeanLocal;
import entity.BuildMaster;
import entity.GlobalRoles;
import entity.LocalRoles;
import entity.LocalRolesUser;
import entity.ProjectMaster;
import entity.RepoCredential;
import entity.UserMaster;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author pankti
 */
@Path("cicd")
public class CICDResource {

    @EJB FunctionBeanLocal fbl;
    @Context
    private UriInfo context;
    @Inject  private TokenProvider tokenProvider;

    /**
     * Creates a new instance of CICDResource
     */
    public CICDResource() {
        //fbl.startAllTimers();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<UserMaster> getAllUsers() {
    Collection<UserMaster> users=fbl.getAllUsers();
    return  users;
    }
    
     @GET
    @Path("getRoles")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<GlobalRoles> getAllGlobalRoles() {
    Collection<GlobalRoles> globalRoleses=fbl.getAllGlobalRoles();
    return  globalRoleses;
    }
    
     @GET
    @Path("getGlobalRoleByNam/{rname}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
  
    public GlobalRoles getGlobalRole(@PathParam("rname") String role) {
    GlobalRoles globalRoles=fbl.getGlobalRole(role);
    return globalRoles;
    }

    @POST
    @Path("addGlobalRole/{role}/{projectCreate}/{projectRemove}/{projectExecute}/{bulid}/{buildHistory}/{credentialView}/{credentialCreate}/{credentialDelete}")
    public void addGlobalRole(@PathParam("role") String role, 
            @PathParam("projectCreate")  Boolean projectCreate, @PathParam("projectRemove") Boolean projectRemove, 
            @PathParam("projectExecute") Boolean projectExecute, @PathParam("bulid") Boolean bulid,@PathParam("buildHistory") Boolean buildHistory, 
            @PathParam("credentialView") Boolean credentialView,
           @PathParam("credentialCreate") Boolean credentialCreate, @PathParam("credentialDelete") Boolean credentialDelete) {
       
        fbl.addGlobalRole(role, projectCreate, projectRemove, projectExecute, bulid, buildHistory, credentialView, credentialCreate, credentialDelete);

    }

    
    @POST
    @Path("changeGlobalRole/{id}/{role}/{projectCreate}/{projectRemove}/{projectExecute}/{bulid}/{buildHistory}/{credentialView}/{credentialCreate}/{credentialDelete}")
    public void changeGlobalRole(
            @PathParam("id") Integer id, @PathParam("role") String role, 
            @PathParam("projectCreate")  Boolean projectCreate,@PathParam("projectRemove") Boolean projectRemove, 
            @PathParam("projectExecute") Boolean projectExecute,@PathParam("bulid") Boolean bulid,@PathParam("buildHistory") Boolean buildHistory, 
            @PathParam("credentialView") Boolean credentialView,
           @PathParam("credentialCreate") Boolean credentialCreate,@PathParam("credentialDelete") Boolean credentialDelete) {
         fbl.changeGlobalRole(id, role, projectCreate, projectRemove, projectExecute, bulid, buildHistory, credentialView, credentialCreate, credentialDelete);
  
    }


    @DELETE
    @Path("removeGlobalRole/{roleId}")
    public void removeGlobalRole(@PathParam("roleId")Integer roleId) {
    fbl.removeGlobalRole(roleId);
    }
    
    
     @GET
    @Path("getUserByName/{name}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<UserMaster> getUserByName(@PathParam("name") String name) {
        
    Collection<UserMaster> users=fbl.getUserByName(name);
    return users;
    }


    
    @POST
    @Path("updateUser/{userId}/{name}/{email}/{receivesEmail}/{golbalRoleId}")
    public void updateUser(@PathParam("userId") Integer userId, @PathParam("name") String name, 
            @PathParam("email") String email
            , @PathParam("receivesEmail") Boolean receivesEmail, @PathParam("golbalRoleId") Integer golbalRoleId) {
       fbl.updateUser(userId, name, email,receivesEmail,golbalRoleId);
    }

    @DELETE
    @Path("removeUser/{userId}")
    public void removeUser(@PathParam("userId") Integer userId) {
        fbl.removeUser(userId);
    }
    
    
    @GET
    @Path("getUsersOfGlobalRole/{role}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<UserMaster> getUsersOfGlobalRole(@PathParam("role")String role) {
     Collection<UserMaster>users=fbl.getUsersOfGlobalRole(role);
     return  users;
    }

    @GET 
    @Path("getAllRepoCredential")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<RepoCredential> getAllRepoCredentials() {
    Collection<RepoCredential> repos=fbl.getAllRepoCredentials();
    return repos;
    }
 
    
    
    @GET 
    @Path("getAllRepoCredentialsByUser/{userId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<RepoCredential> getAllRepoCredentialsByUser(@PathParam("userId")Integer userId) {
  
    return  fbl.getAllRepoCredentialsByUser(userId);
    }
        

   @POST
   @Path("addRepoCredentialToUser/{username}/{password}/{userId}")
    public void addRepoCredentialToUser(@PathParam("username")String username,@PathParam("password") String password,@PathParam("userId")Integer userId) {
    fbl.addRepoCredentialToUser(username, password, userId);
    }

    @POST
    @Path("updateRepoCredentialOfUser/{credentialId}/{username}/{password}/{userId}")
    public void updateRepoCredentialOfUser(@PathParam("credentialId") Integer credentialId,
            @PathParam("username") String username, 
            @PathParam("password") String password, @PathParam("userId") Integer userId) {
           fbl.updateRepoCredentialOfUser(credentialId, username, password, userId);
    }

    
    @DELETE
    @Path("removeRepoCredentialOfUser/{userId}/{credentialId}")
    public void removeRepoCredentialOfUser(@PathParam("userId")Integer userId,@PathParam("credentialId")Integer credentialId) {
       fbl.removeRepoCredentialOfUser(userId, credentialId);   
    }
    
    
    @GET
    @Path("getAllProjects")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<ProjectMaster> getAllProjects()
    {
        Collection<ProjectMaster> cp = fbl.getAllProjects();
        return cp;
    }
    
    @POST
    @Path("createProject/{projectName}/{shellCommand}/{timeInterval}")
    public void createProject(@PathParam("projectName")String projectName ,@PathParam("shellCommand") String shellCommand,
            @PathParam("timeInterval")String timeInterval)
    {
        try{
        fbl.createProject(projectName, URLDecoder.decode(shellCommand, "UTF-8"), URLDecoder.decode(timeInterval, "UTF-8"));
        }
        catch(Exception e){}
    }

    @POST
    @Path("createProjectWithPhysicalpath/{projectName}/{shellCommand}/{timeInterval}/{phypath}")
    public void createProjectWithPhysicalpath(@PathParam("projectName")String projectName,
            @PathParam("shellCommand") String shellCommand,
            @PathParam("timeInterval")String timeInterval,
            @PathParam("phypath")String phypath 
            )
    {
        try{
            fbl.createProject(projectName, URLDecoder.decode(shellCommand, "UTF-8"), URLDecoder.decode(timeInterval, "UTF-8"), URLDecoder.decode(phypath, "UTF-8"));
        }
        catch(Exception e){}
    }
    
    @POST
    @Path("createProjectWithGitUrl/{projectName}/{shellCommand}/{timeInterval}/{phypath}/{giturl}")
    public void createProjectWithGitUrl(
       @PathParam("projectName")String projectName,
       @PathParam("shellCommand") String shellCommand,
       @PathParam("timeInterval")String timeInterval,
       @PathParam("phypath")String phypath,
       @PathParam("giturl")String giturl
       
    )
    {
        try{
        fbl.createProject(projectName, URLDecoder.decode(shellCommand, "UTF-8"), URLDecoder.decode(timeInterval, "UTF-8"), URLDecoder.decode(phypath, "UTF-8"), URLDecoder.decode(giturl, "UTF-8"));
        }
        catch(Exception e){}
    }
    
    
    
    @POST
    @Path("createProjectWithGitCredential/{projectName}/{shellCommand}/{timeInterval}/{phypath}/{giturl}/{username}/{password}")
    public void createProjectWithGitCredential(
       @PathParam("projectName")String projectName,
       @PathParam("shellCommand") String shellCommand,
       @PathParam("timeInterval")String timeInterval,
       @PathParam("phypath")String phypath,
       @PathParam("giturl")String giturl,
       @PathParam("username")String username,
       @PathParam("password")String password,
       @Context HttpHeaders headers
    )
    {
        List<String> s = headers.getRequestHeader(AUTHORIZATION_HEADER);
        String jwt = s.get(0).split(" ")[1];
        String user;
        JWTCredential credential = tokenProvider.getCredential(jwt);
        user = credential.getPrincipal();
        int credentialId=fbl.getRepoCredentialId(username, password);
        try{
        fbl.createProject(projectName, URLDecoder.decode(shellCommand, "UTF-8"), URLDecoder.decode(timeInterval, "UTF-8"), URLDecoder.decode(phypath, "UTF-8"), URLDecoder.decode(giturl, "UTF-8"), username, password, user);
        }
        catch(Exception e){}
    }
    
    
    
      

   


    @GET
    @Path("getLocalRole")
     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<LocalRoles> getLocalRole() {
    Collection<LocalRoles> roles=fbl.getLocalRole();
    return  roles;
    
    }

    @GET
    @Path("getLocalRoleByRole/{role}")
     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<LocalRoles> getLocalRoleByRole(@PathParam("role")String role) {
    Collection<LocalRoles> roles=fbl.getLocalRoleByRole(role);
    return roles;
    }

    @GET
    @Path("getLocalRoleByProject/{project}")
     @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<LocalRoles> getLocalRoleByProject(@PathParam("project")Integer projectId) {
    Collection<LocalRoles> roles=fbl.getLocalRoleByProject(projectId);
    return roles;
    }

    
    @DELETE
    @Path("removeLocalRole/{roleId}")
    public void removeLocalRole(@PathParam("roleId")Integer roleId) {
    fbl.removeLocalRole(roleId);
        
    }
    
    @GET
    @Path("getLocalRolesUsersByUser/{userId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<LocalRolesUser> getLocalRolesUsersByUser(@PathParam("userId")Integer userId) {
        Collection<LocalRolesUser> localRolesUsers=fbl.getLocalRolesUsersByUser(userId);
        return localRolesUsers;
     
    }

    @POST
    @Path("addLocalRoleUser/{roleId}/{userRoleId}")
    public void addLocalRoleUser(@PathParam("roleId")Integer roleId,@PathParam("userRoleId") Integer userRoleId) {
        fbl.addLocalRoleUser(roleId, userRoleId);
    
    }
    
    @POST
    @Path("startAllTimers")
    public void startAllTimers()
    {
        fbl.startAllTimers();
    }
   
    @POST
    @Path("stopAllTimers")
    public void stopAllTimers()
    {
        fbl.stopAllTimers();
    }
    
    @POST
    @Path("startTimer/{projectName}")
    public void startTimer(@PathParam("projectName")String projectName)
    {
        fbl.startTimer(projectName);
    }
    
    @POST
    @Path("stopTimer/{projectName}")
    public void stopTimer(@PathParam("projectName")String projectName)
    {
        fbl.stopTimer(projectName);
    }
    


    
    @POST
      @Path("addLocalRole/{role}/{projectId}/{projectCreate}/{projectRemove}/{projectExecute}/{build}/{ buildHistory}")
     public void addLocalRole(@PathParam("role")String role,@PathParam("projectId") Integer projectId,@PathParam("projectCreate") Boolean projectCreate,
            @PathParam("projectRemove")Boolean projectRemove,@PathParam("projectExecute") Boolean projectExecute,@PathParam("build") Boolean build, 
            @PathParam("buildHistory")Boolean buildHistory) {
         fbl.addLocalRole(role, projectId, projectCreate, projectRemove, projectExecute, build, buildHistory);
     }
     
     
     @POST
     @Path("changeLocalRole/{roleId}/{role}/{projectId}/{projectCreate}/{projectRemove}/{projectExecute}/{build}/{ buildHistory}")
    public void changeLocalRole(@PathParam("roleId")Integer roleId,@PathParam("role")String role,@PathParam("projectId") Integer projectId,@PathParam("projectCreate") Boolean projectCreate,
            @PathParam("projectRemove")Boolean projectRemove,@PathParam("projectExecute") Boolean projectExecute,@PathParam("build") Boolean build, 
            @PathParam("buildHistory")Boolean buildHistory) {
    fbl.changeLocalRole(roleId, role, projectId, projectCreate, projectRemove, projectExecute, build, buildHistory);
    
    }
    
    @DELETE
    @Path("removeLocalRoleUser/{id}")
    public void removeLocalRoleUser(@PathParam("id")Integer id) {
    //fbl.removeLocalRoleUser(id);
    }
    
    @POST
    @Path("getUserByEmail/{email}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public UserMaster getUserByEmail(@PathParam("email")String email) {
    UserMaster user=fbl.getUserByEmail(email);
    return user;
    }
    
    @POST
    @Path("getLocalRolesUser/{userid}/{localroleid}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    
    public LocalRolesUser getLocalRolesUser(@PathParam("userid")Integer userid,@PathParam("localroleid")Integer localroleid) {
  LocalRolesUser l=fbl.getLocalRolesUser(userid, localroleid);
    return l;
    
    }
    
    @GET
    @Path("getLocalRolesUsers")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<LocalRolesUser> getLocalRolesUsers()
    {
        Collection<LocalRolesUser> lrus=fbl.getLocalRolesUsers();
        return  lrus;
    }
    
    @GET
    @Path("getBuilds")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<BuildMaster> getAllBuilds()
    {
        Collection<BuildMaster> bm=fbl.getAllBuild();
        return bm;
    }
    
    @GET
    @Path("getBuild/{projectId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Collection<BuildMaster> getBuildByProject(@PathParam("projectId")Integer projectId)
    {
        Collection<BuildMaster> bm=fbl.getBuildOfProject(projectId);
        return bm;
    }
    
    @GET
    @Path("getbuild/{buildId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public BuildMaster getBuild(@PathParam("buildId")Integer buildId) {
        BuildMaster bm = fbl.getBuild(buildId);
        return bm;
    }
    
    
    @POST
    @Path("updateProject/{projectId}/{projectName}/{shellCommand}/{timeInterval}")
    public void updateProject(@PathParam("projectId")Integer projectId, @PathParam("projectName")String projectName ,@PathParam("shellCommand") String shellCommand,
            @PathParam("timeInterval")String timeInterval)
    {
        try{
        fbl.updateProject(projectId, projectName, URLDecoder.decode(shellCommand, "UTF-8"), URLDecoder.decode(timeInterval, "UTF-8"));
        }
        catch(Exception e){}
    }

    @POST
    @Path("updateProjectWithPhysicalpath/{projectId}/{projectName}/{shellCommand}/{timeInterval}/{phypath}")
    public void updateProjectWithPhysicalpath(@PathParam("projectId")Integer projectId, @PathParam("projectName")String projectName,
            @PathParam("shellCommand") String shellCommand,
            @PathParam("timeInterval")String timeInterval,
            @PathParam("phypath")String phypath 
            )
    {
        try{
            fbl.updateProject(projectId, projectName, URLDecoder.decode(shellCommand, "UTF-8"), URLDecoder.decode(timeInterval, "UTF-8"), URLDecoder.decode(phypath, "UTF-8"));
        }
        catch(Exception e){}
    }
    
    @POST
    @Path("updateProjectWithGitUrl/{projectId}/{projectName}/{shellCommand}/{timeInterval}/{phypath}/{giturl}")
    public void updateProjectWithGitUrl(
       @PathParam("projectId")Integer projectId, 
       @PathParam("projectName")String projectName,
       @PathParam("shellCommand") String shellCommand,
       @PathParam("timeInterval")String timeInterval,
       @PathParam("phypath")String phypath,
       @PathParam("giturl")String giturl
       
    )
    {
        try{
        fbl.updateProject(projectId, projectName, URLDecoder.decode(shellCommand, "UTF-8"), URLDecoder.decode(timeInterval, "UTF-8"), URLDecoder.decode(phypath, "UTF-8"), URLDecoder.decode(giturl, "UTF-8"));
        }
        catch(Exception e){}
    }
    
    
    
    @POST
    @Path("updateProjectWithGitCredential/{projectId}/{projectName}/{shellCommand}/{timeInterval}/{phypath}/{giturl}/{username}/{password}")
    public void updateProjectWithGitCredential(
        @PathParam("projectId")Integer projectId, 
       @PathParam("projectName")String projectName,
       @PathParam("shellCommand") String shellCommand,
       @PathParam("timeInterval")String timeInterval,
       @PathParam("phypath")String phypath,
       @PathParam("giturl")String giturl,
       @PathParam("username")String username,
       @PathParam("password")String password,
       @Context HttpHeaders headers
    )
    {
        List<String> s = headers.getRequestHeader(AUTHORIZATION_HEADER);
        String jwt = s.get(0).split(" ")[1];
        String user;
        JWTCredential credential = tokenProvider.getCredential(jwt);
        user = credential.getPrincipal();
        int credentialId=fbl.getRepoCredentialId(username, password);
        try{
        fbl.updateProject(projectId, projectName, URLDecoder.decode(shellCommand, "UTF-8"), URLDecoder.decode(timeInterval, "UTF-8"), URLDecoder.decode(phypath, "UTF-8"), URLDecoder.decode(giturl, "UTF-8"), username, password, user);
        }
        catch(Exception e){}
    }
    
    @GET
    @Path("getProject/{projectId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public ProjectMaster getProject(@PathParam("projectId")Integer projectId) {
        ProjectMaster pm = fbl.getProject(projectId);
        return pm;
    }
    
    @GET
    @Path("checkLocalRights/{userid}/{projectid}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public ProjectMaster checkLocalRights(@PathParam("userid")Integer userid,@PathParam("projectid") Integer projectid) {
    ProjectMaster projectMaster=fbl.checkLocalRights(userid, projectid);
    return projectMaster;
    }
    
    
    @POST
    @Path("registerUserWithRole/{name}/{email}/{password}/{receivesEmail}/{golbalRoleId}")
  
    public void registerUserWithRole(@PathParam("name") String name,@PathParam("email") String email,@PathParam("password") String password
            ,@PathParam("receivesEmail") Boolean receivesEmail,@PathParam("golbalRoleId") Integer golbalRoleId) {
      
        fbl.registerUser(name, email,password, receivesEmail, golbalRoleId);
    }
    
  
    @POST
    @Path("registerUser/{name}/{email}/{password}/{receivesEmail}")
    public void registerUser(@PathParam("name") String name,@PathParam("email") String email,@PathParam("password") String password
            ,@PathParam("receivesEmail") Boolean receivesEmail) {
    fbl.registerUser(name, email, password, receivesEmail);
    }
    
    @POST
    @Path("buildProject/{projectId}")
    public void buildProject(@PathParam("projectId")Integer projectId)
    {
        fbl.buildProject(projectId);
    }
    
    @GET
    @Path("getLocalRoleByProjectAndUser/{project}/{user}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public LocalRoles getLocalRoleByProjectAndUser(@PathParam("project")Integer projectId,@PathParam("user")Integer userId) {
    LocalRoles roles=fbl.getLocalRoleByProjectAndUser(projectId , userId);
    return roles;
    }
    
    
}
