/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import filters.AuthFilter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:CICDResource [cicd]<br>
 * USAGE:
 * <pre>
 *        CICDClient client = new CICDClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author smart
 */
public class CICDClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "https://localhost:8181/CICDApp/webresources";

    public CICDClient(String token) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new AuthFilter(token));
        webTarget = client.target(BASE_URI).path("cicd");
    }
    public CICDClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        client.register(new AuthFilter(""));
        webTarget = client.target(BASE_URI).path("cicd");
    }
    static {
        //for localhost testing only
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {

            public boolean verify(String hostname,
                    javax.net.ssl.SSLSession sslSession) {
                if (hostname.equals("localhost")) {
                    return true;
                }
                return false;
            }
        });
    }
    public <T> T getUsersOfGlobalRole_XML(Class<T> responseType, String role) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUsersOfGlobalRole/{0}", new Object[]{role}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getUsersOfGlobalRole_JSON(Class<T> responseType, String role) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUsersOfGlobalRole/{0}", new Object[]{role}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void createProject(String projectName, String shellCommand, String timeInterval) throws ClientErrorException {
        try{
            shellCommand = URLEncoder.encode(shellCommand, "UTF-8");
            timeInterval= URLEncoder.encode(timeInterval, "UTF-8");
        }
        catch(Exception r){}
        webTarget.path(java.text.MessageFormat.format("createProject/{0}/{1}/{2}", new Object[]{projectName, shellCommand, timeInterval})).request().post(null);
    }

    public <T> T getLocalRoleByProject_XML(Class<T> responseType, String project) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getLocalRoleByProject/{0}", new Object[]{project}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getLocalRoleByProject_JSON(Class<T> responseType, String project) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getLocalRoleByProject/{0}", new Object[]{project}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllGlobalRoles_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getRoles");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllGlobalRoles_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getRoles");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addLocalRole(String role, String projectId, String projectCreate, String projectRemove, String projectExecute, String build, String buildHistory) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addLocalRole/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{role, projectId, projectCreate, projectRemove, projectExecute, build, buildHistory})).request().post(null);
    }

    public <T> T getLocalRoleByRole_XML(Class<T> responseType, String role) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getLocalRoleByRole/{0}", new Object[]{role}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getLocalRoleByRole_JSON(Class<T> responseType, String role) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getLocalRoleByRole/{0}", new Object[]{role}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeUser(String userId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeUser/{0}", new Object[]{userId})).request().delete();
    }

    public void addRepoCredentialToUser(String username, String password, String userId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addRepoCredentialToUser/{0}/{1}/{2}", new Object[]{username, password, userId})).request().post(null);
    }

    public void updateRepoCredentialOfUser(String credentialId, String username, String password, String userId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateRepoCredentialOfUser/{0}/{1}/{2}/{3}", new Object[]{credentialId, username, password, userId})).request().post(null);
    }

    public void removeLocalRole(String roleId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeLocalRole/{0}", new Object[]{roleId})).request().delete();
    }

    public <T> T getLocalRole_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getLocalRole");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getLocalRole_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getLocalRole");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeGlobalRole(String roleId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeGlobalRole/{0}", new Object[]{roleId})).request().delete();
    }

    public <T> T getAllUsers_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllUsers_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void removeLocalRoleUser(String roleId, String userRoleId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeLocalRoleUser/{0}/{1}", new Object[]{roleId, userRoleId})).request().delete();
    }

    public <T> T getAllRepoCredentials_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllRepoCredential");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllRepoCredentials_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllRepoCredential");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }


    public void updateUser(String userId, String name, String email, String receivesEmail, String golbalRoleId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateUser/{0}/{1}/{2}/{3}/{4}", new Object[]{userId, name, email, receivesEmail, golbalRoleId})).request().post(null);
    }
    
    public <T> T getAllProjects_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllProjects");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllProjects_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllProjects");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void createProjectWithGitCredential(String projectName, String shellCommand, String timeInterval, String phypath ,String giturl, String username, String password) throws ClientErrorException {
        String cmd="";
        try{
            shellCommand = URLEncoder.encode(shellCommand, "UTF-8");
            timeInterval= URLEncoder.encode(timeInterval, "UTF-8");
            phypath = URLEncoder.encode(phypath, "UTF-8");
            giturl = URLEncoder.encode(giturl, "UTF-8");
        }
        catch(Exception e){}
        webTarget.path(java.text.MessageFormat.format("createProjectWithGitCredential/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{projectName, shellCommand, timeInterval, phypath, giturl ,username, password})).request().post(null);
    }

    public void createProjectWithGitUrl(String projectName, String shellCommand, String timeInterval, String phypath, String giturl) throws ClientErrorException {
        try{
            shellCommand = URLEncoder.encode(shellCommand, "UTF-8");
            timeInterval= URLEncoder.encode(timeInterval, "UTF-8");
            phypath = URLEncoder.encode(phypath, "UTF-8");
            giturl = URLEncoder.encode(giturl, "UTF-8");        
        }
        catch(Exception e){}
        webTarget.path(java.text.MessageFormat.format("createProjectWithGitUrl/{0}/{1}/{2}/{3}/{4}", new Object[]{projectName, shellCommand, timeInterval, phypath, giturl})).request().post(null);
    }

    public <T> T getAllRepoCredentialsByUser_XML(Class<T> responseType, String userId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllRepoCredentialsByUser/{0}", new Object[]{userId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllRepoCredentialsByUser_JSON(Class<T> responseType, String userId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getAllRepoCredentialsByUser/{0}", new Object[]{userId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addLocalRoleUser(String roleId, String userRoleId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addLocalRoleUser/{0}/{1}", new Object[]{roleId, userRoleId})).request().post(null);
    }

    public <T> T getLocalRolesUsersByUser_XML(Class<T> responseType, String userId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getLocalRolesUsersByUser/{0}", new Object[]{userId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getLocalRolesUsersByUser_JSON(Class<T> responseType, String userId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getLocalRolesUsersByUser/{0}", new Object[]{userId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void createProjectWithPhysicalpath(String projectName, String shellCommand, String timeInterval, String phypath) throws ClientErrorException {
        try{
            shellCommand = URLEncoder.encode(shellCommand, "UTF-8");
            timeInterval= URLEncoder.encode(timeInterval, "UTF-8");
            phypath = URLEncoder.encode(phypath, "UTF-8");
        }
        catch(Exception e){}
        webTarget.path(java.text.MessageFormat.format("createProjectWithPhysicalpath/{0}/{1}/{2}/{3}", new Object[]{projectName, shellCommand, timeInterval, phypath})).request().post(null);
    }

    public void changeLocalRole(String roleId, String role, String projectId, String projectCreate, String projectRemove, String projectExecute, String build, String buildHistory) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("changeLocalRole/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{roleId, role, projectId, projectCreate, projectRemove, projectExecute, build, buildHistory})).request().post(null);
    }


    public <T> T getUserByName_XML(Class<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserByName/{0}", new Object[]{name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getUserByName_JSON(Class<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserByName/{0}", new Object[]{name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addGlobalRole(String role, String projectCreate, String projectRemove, String projectExecute, String bulid, String buildHistory, String credentialView, String credentialCreate, String credentialDelete) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addGlobalRole/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{role, projectCreate, projectRemove, projectExecute, bulid, buildHistory, credentialView, credentialCreate, credentialDelete})).request().post(null);
    }

    public void removeRepoCredentialOfUser(String userId, String credentialId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeRepoCredentialOfUser/{0}/{1}", new Object[]{userId, credentialId})).request().delete();
    }

    public void changeGlobalRole(String id, String role, String projectCreate, String projectRemove, String projectExecute, String bulid, String buildHistory, String credentialView, String credentialCreate, String credentialDelete) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("changeGlobalRole/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{id, role, projectCreate, projectRemove, projectExecute, bulid, buildHistory, credentialView, credentialCreate, credentialDelete})).request().post(null);
    }

    public <T> T getGlobalRole_XML(Class<T> responseType, String rname) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getGlobalRoleByNam/{0}", new Object[]{rname}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getGlobalRole_JSON(Class<T> responseType, String rname) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getGlobalRoleByNam/{0}", new Object[]{rname}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    public void startAllTimers() throws ClientErrorException {
        webTarget.path("startAllTimers").request().post(null);
    }
    public void startTimer(String projectName) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("startTimer/{0}", new Object[]{projectName})).request().post(null);
    }    
        public void stopAllTimers() throws ClientErrorException {
        webTarget.path("stopAllTimers").request().post(null);
    }
        public void stopTimer(String projectName) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("stopTimer/{0}", new Object[]{projectName})).request().post(null);
    }
    
    public <T> T getLocalRolesUsers_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getLocalRolesUsers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getLocalRolesUsers_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getLocalRolesUsers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
        public <T> T getLocalRolesUser(Class<T> responseType, String userid, String localroleid) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("getLocalRolesUser/{0}/{1}", new Object[]{userid, localroleid})).request().post(null, responseType);
    }
    public <T> T getUserByEmail(Class<T> responseType, String email) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("getUserByEmail/{0}", new Object[]{email})).request().post(null, responseType);
    }
    
    
    public <T> T getAllBuilds_XML(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getBuilds");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getAllBuilds_JSON(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getBuilds");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
    
    public <T> T getBuildByProject_XML(Class<T> responseType, String projectId) throws ClientErrorException {
    WebTarget resource = webTarget;
    resource = resource.path(java.text.MessageFormat.format("getBuild/{0}", new Object[]{projectId}));
    return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getBuildByProject_JSON(Class<T> responseType, String projectId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getBuild/{0}", new Object[]{projectId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
        public <T> T getBuild_XML(Class<T> responseType, String buildId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getbuild/{0}", new Object[]{buildId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getBuild_JSON(Class<T> responseType, String buildId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getbuild/{0}", new Object[]{buildId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
        public void updateProjectWithGitUrl(String projectId, String projectName, String shellCommand, String timeInterval, String phypath, String giturl) throws ClientErrorException {
        
        try{
            shellCommand = URLEncoder.encode(shellCommand, "UTF-8");
            timeInterval= URLEncoder.encode(timeInterval, "UTF-8");
            phypath = URLEncoder.encode(phypath, "UTF-8");
            giturl = URLEncoder.encode(giturl, "UTF-8");        
        }
        catch(Exception e){}
        webTarget.path(java.text.MessageFormat.format("updateProjectWithGitUrl/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{projectId, projectName, shellCommand, timeInterval, phypath, giturl})).request().post(null);
    }

        public void updateProjectWithGitCredential(String projectId, String projectName, String shellCommand, String timeInterval, String phypath, String giturl, String username, String password) throws ClientErrorException {
            
            String cmd="";
            try{
                shellCommand = URLEncoder.encode(shellCommand, "UTF-8");
                timeInterval= URLEncoder.encode(timeInterval, "UTF-8");
                phypath = URLEncoder.encode(phypath, "UTF-8");
                giturl = URLEncoder.encode(giturl, "UTF-8");
            }
            catch(Exception e){}
            webTarget.path(java.text.MessageFormat.format("updateProjectWithGitCredential/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{projectId, projectName, shellCommand, timeInterval, phypath, giturl, username, password})).request().post(null);
        }

        public void updateProjectWithPhysicalpath(String projectId, String projectName, String shellCommand, String timeInterval, String phypath) throws ClientErrorException {
                try{
                    shellCommand = URLEncoder.encode(shellCommand, "UTF-8");
                    timeInterval= URLEncoder.encode(timeInterval, "UTF-8");
                    phypath = URLEncoder.encode(phypath, "UTF-8");
                }
                catch(Exception e){}
            
                webTarget.path(java.text.MessageFormat.format("updateProjectWithPhysicalpath/{0}/{1}/{2}/{3}/{4}", new Object[]{projectId, projectName, shellCommand, timeInterval, phypath})).request().post(null);
             }
        public void updateProject(String projectId, String projectName, String shellCommand, String timeInterval) throws ClientErrorException {
        try{
            shellCommand = URLEncoder.encode(shellCommand, "UTF-8");
            timeInterval= URLEncoder.encode(timeInterval, "UTF-8");
        }
        catch(Exception r){}
            
            webTarget.path(java.text.MessageFormat.format("updateProject/{0}/{1}/{2}/{3}", new Object[]{projectId, projectName, shellCommand, timeInterval})).request().post(null);
    }
        
    public <T> T getProject_XML(Class<T> responseType, String projectId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProject/{0}", new Object[]{projectId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getProject_JSON(Class<T> responseType, String projectId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProject/{0}", new Object[]{projectId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
    
    public <T> T checkLocalRights_XML(Class<T> responseType, String userid, String projectid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("checkLocalRights/{0}/{1}", new Object[]{userid, projectid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T checkLocalRights_JSON(Class<T> responseType, String userid, String projectid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("checkLocalRights/{0}/{1}", new Object[]{userid, projectid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }
    
    public void registerUserWithRole(String name, String email, String password, String receivesEmail, String golbalRoleId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("registerUserWithRole/{0}/{1}/{2}/{3}/{4}", new Object[]{name, email, password, receivesEmail, golbalRoleId})).request().post(null);
    }
    public void registerUser(String name, String email, String password, String receivesEmail) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("registerUser/{0}/{1}/{2}/{3}", new Object[]{name, email, password, receivesEmail})).request().post(null);
    }
    
    public void buildProject(String projectId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("buildProject/{0}", new Object[]{projectId})).request().post(null);
    }
    
    public <T> T getLocalRoleByProjectAndUser_XML(Class<T> responseType, String project, String user) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getLocalRoleByProjectAndUser/{0}/{1}", new Object[]{project, user}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    public <T> T getLocalRoleByProjectAndUser_JSON(Class<T> responseType, String project, String user) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getLocalRoleByProjectAndUser/{0}/{1}", new Object[]{project, user}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    
}
