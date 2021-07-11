/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.BuildMaster;
import entity.GlobalRoles;
import entity.LocalRoles;
import entity.LocalRolesUser;
import entity.ProjectMaster;
import entity.RepoCredential;
import entity.UserMaster;
import java.util.Collection;
import javax.ejb.Local;

/**
 *
 * @author pankti
 */
@Local
    public interface FunctionBeanLocal {
                Collection<GlobalRoles> getAllGlobalRoles();
                GlobalRoles getGlobalRole(String role);
                void addGlobalRole(String role, Boolean projectCreate, Boolean projectRemove, Boolean projectExecute, Boolean bulid, Boolean buildHistory, Boolean credentialView, Boolean credentialCreate, Boolean credentialDelete);
                void changeGlobalRole(Integer id,String role, Boolean projectCreate, Boolean projectRemove, Boolean projectExecute, Boolean bulid, Boolean buildHistory, Boolean credentialView, Boolean credentialCreate, Boolean credentialDelete);
                void removeGlobalRole(Integer roleId);


                Collection<UserMaster> getAllUsers();
                Collection<UserMaster> getUserByName(String name);
                void registerUser(String name, String email, String password, Boolean receivedEmail,Integer roleId);
                void registerUser(String name, String email, String password, Boolean receivedEmail);
                void updateUser(Integer userId,String name, String email, Boolean receivedEmail,Integer roleId);
                void removeUser(Integer userId);
                String changePassword(String email, String old_password,String new_password);

                Collection<UserMaster> getUsersOfGlobalRole(String role);

               Collection<RepoCredential> getAllRepoCredentials();

                  Collection<RepoCredential> getAllRepoCredentialsByUser(Integer userId);
                  void addRepoCredentialToUser(String username, String password,Integer userId);
                  void updateRepoCredentialOfUser(Integer credentialId,String username, String password,Integer userId);
                  void removeRepoCredentialOfUser(Integer userId,Integer credentialId);
                  Integer getRepoCredentialId(String username, String password);
                  
                 //Testing
                  Collection<ProjectMaster> getAllProjects();
                  ProjectMaster getProject(Integer projectId);
                  void createProject(String projectName , String shellCommand, String timeInterval);
                  void createProject(String projectName , String shellCommand, String timeInterval, String physicalPath);
                  void createProject(String projectName , String shellCommand, String timeInterval, String physicalPath, String gitUrl);
                  void createProject(String projectName , String shellCommand, String timeInterval, String physicalPath, String gitUrl, String username ,String password, String user);

                  void updateProject(Integer projectId , String projectName , String shellCommand, String timeInterval);
                  void updateProject(Integer projectId , String projectName , String shellCommand, String timeInterval, String physicalPath);
                  void updateProject(Integer projectId , String projectName , String shellCommand, String timeInterval, String physicalPath, String gitUrl);
                  void updateProject(Integer projectId , String projectName , String shellCommand, String timeInterval, String physicalPath, String gitUrl, String username ,String password, String user);
                  
                  void removeProject(Integer projectId);
                  
                  Collection<LocalRoles> getLocalRole();
                  Collection<LocalRoles> getLocalRoleByRole(String roles);
                  Collection<LocalRoles> getLocalRoleByProject(Integer projectId);
                  void addLocalRole(String role,Integer projectId, Boolean projectCreate, Boolean projectRemove, Boolean projectExecute, Boolean build, Boolean buildHistory);
                  void changeLocalRole(Integer roleId,String role,Integer projectId, Boolean projectCreate, Boolean projectRemove, Boolean projectExecute, Boolean build, Boolean buildHistory);
                  void removeLocalRole(Integer roleId);

                  Collection<LocalRolesUser> getLocalRolesUsers();
                  Collection<LocalRolesUser> getLocalRolesUsersByRole(Integer roleId);
                  Collection<LocalRolesUser> getLocalRolesUsersByUser(Integer userId);
                  LocalRoles getLocalRoleByProjectAndUser(Integer projectId,Integer userId);
                  void addLocalRoleUser(Integer roleId,Integer userRoleId);
                  
                  void removeLocalRoleUser(Integer id,Integer roleid,Integer userid);

                  void startAllTimers();
                  void stopAllTimers();
                  void startTimer(String projectName);
                  void stopTimer(String projectName);
                  Boolean getTimerState(String projectName);
                  
                  void sendEmail(String to,String subject,String body);
                  String forgotpassword(String email);
                  String verifyOTP(String email,String otp);
                  String resetPassword(String email,String password);
                  
                  UserMaster getUserByEmail(String email);
                  UserMaster loginUser(String email, String password);
                  LocalRolesUser getLocalRolesUser(Integer userid,Integer localroleid);
                  
                  Collection<BuildMaster> getBuildOfProject (Integer projectId);
                  Collection<BuildMaster> getAllBuild ();
                  BuildMaster getBuild(Integer buildId);
                  
                  void buildProject(Integer projectId);
                  void buildOnWebhook(String repoUrl);
                  ProjectMaster checkLocalRights(Integer userid,Integer projectid);
}