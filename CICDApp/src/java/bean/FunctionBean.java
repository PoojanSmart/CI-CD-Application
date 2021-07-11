/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import auth.JWTCredential;
import auth.TokenProvider;
import entity.BuildMaster;
import entity.GitMaster;
import entity.GlobalRoles;
import entity.LocalRoles;
import entity.LocalRolesUser;
import entity.LocalRolesUserPK;
import entity.ProjectMaster;
import entity.RepoCredential;
import entity.RepoMaster;
import entity.UserMaster;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import org.eclipse.jgit.api.errors.GitAPIException;
import test.Build;
import test.Timers;

/**
 *
 * @author pankti
 */
@Stateless
public class FunctionBean implements FunctionBeanLocal {
 
    @PersistenceContext(name="projectpu")
    EntityManager em;
 
    @Context private HttpServletRequest request;
    @Inject  private TokenProvider tokenProvider;
    private javax.naming.Context context = null;
    
    @Inject private Timers timerObj;
    @EJB Build b;
    protected Date sendDate;
    
    @Override
    public Collection<GlobalRoles> getAllGlobalRoles() {
    Collection<GlobalRoles> globalRoleses=em.createNamedQuery("GlobalRoles.findAll").getResultList();
    return  globalRoleses;
    }

    @Override
    public GlobalRoles getGlobalRole(String role) {
    GlobalRoles globalRoles=(GlobalRoles) em.createNamedQuery("GlobalRoles.findByRole").setParameter("role", role).getResultList().stream().findFirst().orElse(null);
    return globalRoles;
    }

    @Override
    public void addGlobalRole(String role, Boolean projectCreate, Boolean projectRemove, Boolean projectExecute, Boolean bulid, Boolean buildHistory, Boolean credentialView, Boolean credentialCreate, Boolean credentialDelete) {
    GlobalRoles addGlobalRole=new GlobalRoles(role, projectCreate, projectRemove, projectExecute, bulid, buildHistory, credentialView, credentialCreate, credentialDelete);
    em.persist(addGlobalRole);
    }

    @Override
    public void changeGlobalRole(Integer id, String role, Boolean projectCreate, Boolean projectchanRemove, Boolean projectExecute, Boolean bulid, Boolean buildHistory, Boolean credentialView, Boolean credentialCreate, Boolean credentialDelete) {
    GlobalRoles changeGlobalRole=em.find(GlobalRoles.class, id);
    if(changeGlobalRole!=null)
    {
    changeGlobalRole.setRole(role);
    changeGlobalRole.setProjectCreate(projectCreate);
    changeGlobalRole.setProjectRemove(projectchanRemove);
    changeGlobalRole.setProjectExecute(projectExecute);
    changeGlobalRole.setBulid(bulid);
    changeGlobalRole.setBuildHistory(buildHistory);
    changeGlobalRole.setCredentialView(credentialView);
    changeGlobalRole.setCredentialCreate(credentialCreate);
    changeGlobalRole.setCredentialDelete(credentialDelete);
    em.merge(changeGlobalRole);
    }    
    
    }


    @Override
    public void removeGlobalRole(Integer roleId) {
    GlobalRoles removeGlobalRole=em.find(GlobalRoles.class,roleId);
     if(removeGlobalRole!=null)
     {
         em.remove(removeGlobalRole);
     }
    }
    
    
     @Override
    public Collection<UserMaster> getAllUsers() {
    Collection<UserMaster> users=em.createNamedQuery("UserMaster.findAll").getResultList();
    return users;
    }

    @Override
    public Collection<UserMaster> getUserByName(String name) {
        
    Collection<UserMaster> users=em.createNamedQuery("UserMaster.findByName").setParameter("name", name).getResultList();
    return users;
    }

    @Override
    public void registerUser(String name, String email, String password, Boolean receivesEmail,Integer golbalRoleId) {
    GlobalRoles role=em.find(GlobalRoles.class,golbalRoleId);
    UserMaster addUser=new UserMaster(name, email,password, receivesEmail,role);
    Collection<UserMaster> users=role.getUserMasterCollection();
    users.add(addUser);
    em.persist(addUser);
    em.merge(role);
    }
    
    @Override
    public void registerUser(String name, String email, String password, Boolean receivesEmail) {
    GlobalRoles role=em.find(GlobalRoles.class,1);
    UserMaster addUser=new UserMaster(name, email,password, receivesEmail,role);
    Collection<UserMaster> users=role.getUserMasterCollection();
    users.add(addUser);
    role.setUserMasterCollection(users);
    em.persist(addUser);
    em.merge(role);
    }
    
    
    
    
    
    @Override
    public void updateUser(Integer userId, String name, String email,Boolean receivedEmail,Integer roleId) {
   
     UserMaster updateUser=em.find(UserMaster.class,userId);
     GlobalRoles role=em.find(GlobalRoles.class,roleId);
     Collection<UserMaster> users=em.createNamedQuery("UserMaster.findAll").getResultList();
     if(users.contains(updateUser))
     {
     users.remove(updateUser);
     updateUser.setName(name);
     updateUser.setEmail(email);
     updateUser.setReceivesEmail(receivedEmail);
     updateUser.setGlobalRoleId(role);
     users.add(updateUser);
     role.setUserMasterCollection(users);
     em.persist(updateUser);
     em.merge(role);
     }
    }

    @Override
    public void removeUser(Integer userId) {
     UserMaster removeUser=em.find(UserMaster.class,userId);
     GlobalRoles role=removeUser.getGlobalRoleId();
     Collection<UserMaster> users=role.getUserMasterCollection();
     if(users.contains(removeUser))
     {
     users.remove(removeUser);
     role.setUserMasterCollection(users);
     em.remove(removeUser);
     em.merge(role);
     }
    }


    @Override
    public String changePassword(String email, String old_password, String new_password) {
             //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        UserMaster users=(UserMaster) em.createNamedQuery("UserMaster.findByLogin").setParameter("email",email ).setParameter("password",old_password).getResultList().stream().findFirst().orElse(null);
        GlobalRoles role=users.getGlobalRoleId();
        Collection<UserMaster> userMasters=role.getUserMasterCollection();
        if(users==null)
        {
         return "There is no login match";
        }
        else
        {
        UserMaster updateLogin=em.find(UserMaster.class,users.getUserId());
        userMasters.remove(updateLogin);
        updateLogin.setPassword(new_password);
        userMasters.add(updateLogin);
        role.setUserMasterCollection(userMasters);
        em.merge(updateLogin);
        em.merge(role);
        return "Successfully";
        }
       
    
    }

    @Override
    public Collection<UserMaster> getUsersOfGlobalRole(String role) {
     GlobalRoles roles=(GlobalRoles) em.createNamedQuery("GlobalRoles.findByRole").setParameter("role", role).getResultList().stream().findFirst().orElse(null);
     Collection<UserMaster> roleUserMasters=roles.getUserMasterCollection();
     return  roleUserMasters;
    }

     @Override
    public Collection<RepoCredential> getAllRepoCredentials() {
    Collection<RepoCredential> repos=em.createNamedQuery("RepoCredential.findAll").getResultList();
    return repos;
    }
 
    @Override
    public Collection<RepoCredential> getAllRepoCredentialsByUser(Integer userId) {
    UserMaster user=em.find(UserMaster.class, userId);
    return  user.getRepoCredentialCollection();
    }

    @Override
    public void addRepoCredentialToUser(String username, String password,Integer userId) {
    UserMaster user=em.find(UserMaster.class, userId);
    Collection<RepoCredential> credentials=user.getRepoCredentialCollection();
    RepoCredential credential=new RepoCredential(username, password);
    credentials.add(credential);
    credential.setUserId(user);
    user.setRepoCredentialCollection(credentials);
    em.persist(credential);
    em.merge(user);
    }

        @Override
    public void updateRepoCredentialOfUser(Integer credentialId, String username, String password, Integer userId) {
      UserMaster user=em.find(UserMaster.class, userId);
      RepoCredential rc=em.find(RepoCredential.class, credentialId);
       Collection<RepoCredential> rcs=em.createNamedQuery("RepoCredential.findAll").getResultList();
       if(rcs.contains(rc))
        {
            rcs.remove(rc);
            rc.setUsername(username);
            rc.setPassword(password);
            rcs.add(rc);
            user.setRepoCredentialCollection(rcs);
            em.persist(rc);
            em.merge(user);
        }
    }

    @Override
    public void removeRepoCredentialOfUser(Integer userId,Integer credentialId) {
        UserMaster user=em.find(UserMaster.class, userId);
        RepoCredential rc=em.find(RepoCredential.class, credentialId);
        Collection<RepoCredential> rcs=user.getRepoCredentialCollection();
        if(rcs.contains(rc))
        {
            rcs.remove(rc);
            user.setRepoCredentialCollection(rcs);
            em.remove(rc);
            em.merge(user);
        }
        
        
        
    }
    
        @Override
    public Integer getRepoCredentialId(String username, String password) {
    RepoCredential credential=(RepoCredential) em.createNamedQuery("RepoCredential.findByCredential").setParameter("username",username).setParameter("password",password).getResultList().stream().findFirst().orElse(null);
        if(credential==null)
        {
         return 0;
        }
        else
        {
            return  credential.getCredentialId();
        }
            
    }
    
    
    //Testing
    
    @Override
    public Collection<ProjectMaster> getAllProjects()
    {
        Collection<ProjectMaster> projects = em.createNamedQuery("ProjectMaster.findAll").getResultList();
        return projects;
    }
    
    @Override
    public void createProject(String projectName , String shellCommand, String timeInterval)
    {
        ProjectMaster p = new ProjectMaster(projectName,shellCommand,timeInterval);
        em.persist(p);
        startTimer(p.getProjectName());

    }


    @Override
    public void createProject(String projectName, String shellCommand, String timeInterval, String physicalPath) {
        
        RepoMaster repo = new RepoMaster(physicalPath);
        em.persist(repo);

        ProjectMaster p = new ProjectMaster(projectName,shellCommand,timeInterval ,repo);
        em.persist(p);
        startTimer(p.getProjectName());

    }

    @Override
    public void createProject(String projectName, String shellCommand, String timeInterval, String physicalPath ,String gitUrl, String username, String password, String user) {
        
        UserMaster u = (UserMaster)em.createNamedQuery("UserMaster.findByEmail").setParameter("email", user).getSingleResult();
        RepoCredential rc = new RepoCredential(username, password);
        rc.setUserId(u);
        em.persist(rc);

        GitMaster git = new GitMaster(gitUrl , rc);
        em.persist(git);

        RepoMaster repo = new RepoMaster(physicalPath , git);
        em.persist(repo);

        ProjectMaster p = new ProjectMaster(projectName,shellCommand,timeInterval ,repo);
        em.persist(p);
        startTimer(p.getProjectName());

    }

    @Override
    public void createProject(String projectName, String shellCommand, String timeInterval, String physicalPath, String gitUrl) {
        
        GitMaster git = new GitMaster(gitUrl);
        em.persist(git);

        RepoMaster repo = new RepoMaster(physicalPath , git);
        em.persist(repo);

        ProjectMaster p = new ProjectMaster(projectName,shellCommand,timeInterval ,repo);
        em.persist(p);
        startTimer(p.getProjectName());

    }

    @Override
    public Collection<LocalRoles> getLocalRole() {
    Collection<LocalRoles> roles=em.createNamedQuery("LocalRoles.findAll").getResultList();
    return roles;
    
    }

    @Override
    public Collection<LocalRoles> getLocalRoleByRole(String roles) {
    Collection<LocalRoles> role=em.createNamedQuery("LocalRoles.findByRole").setParameter("role", roles).getResultList();
    return role;
    }

    @Override
    public Collection<LocalRoles> getLocalRoleByProject(Integer projectId) {
    ProjectMaster projectMaster=em.find(ProjectMaster.class, projectId);
    return projectMaster.getLocalRolesCollection();
    }

    @Override
    public void addLocalRole(String role, Integer projectId, Boolean projectCreate, Boolean projectRemove, Boolean projectExecute, Boolean build, Boolean buildHistory) {
    ProjectMaster projectMaster=em.find(ProjectMaster.class, projectId);
    Collection<LocalRoles> roles=projectMaster.getLocalRolesCollection();
    LocalRoles l=new LocalRoles(role,projectCreate, projectRemove, projectExecute, build, buildHistory);
    /*if(!roles.contains(l))
    {*/
    roles.add(l);
    l.setProjectId(projectMaster);
    projectMaster.setLocalRolesCollection(roles);
    em.persist(l);
    em.merge(projectMaster);
    //}
    }

    @Override
    public void changeLocalRole(Integer roleId, String role, Integer projectId, Boolean projectCreate, Boolean projectRemove, Boolean projectExecute, Boolean build, Boolean buildHistory) {
    ProjectMaster projectMaster=em.find(ProjectMaster.class, projectId);
    LocalRoles l=em.find(LocalRoles.class,roleId);
    Collection<LocalRoles> roles=em.createNamedQuery("LocalRoles.findAll").getResultList();
    if(roles.contains(l))
    {
         roles.remove(l);
         l.setRole(role);
         l.setProjectCreate(projectCreate);
         l.setProjectRemove(projectRemove);
         l.setProjectExecute(projectExecute);
         l.setBuild(build);
         l.setBuildHistory(buildHistory);
         l.setProjectId(projectMaster);
         roles.add(l);
         projectMaster.setLocalRolesCollection(roles);
         em.persist(l);
         em.merge(projectMaster);
    }
    
    }

    @Override
    public void removeLocalRole(Integer roleId) {
     LocalRoles l=em.find(LocalRoles.class,roleId);   
      ProjectMaster projectMaster=l.getProjectId();
    
    Collection<LocalRoles> roles=projectMaster.getLocalRolesCollection();
    if(roles.contains(l))
    {
         roles.remove(l);
         projectMaster.setLocalRolesCollection(roles);
         em.remove(l);
         em.merge(projectMaster);
    }
        
    }

    @Override
    public Collection<LocalRolesUser> getLocalRolesUsers() {
    Collection<LocalRolesUser> localRolesUsers=em.createNamedQuery("LocalRolesUser.findAll").getResultList();
    return  localRolesUsers;
    }

    @Override
    public Collection<LocalRolesUser> getLocalRolesUsersByRole(Integer roleId) {
    LocalRoles roles=em.find(LocalRoles.class, roleId);
    Collection<LocalRolesUser> localRolesUsers=roles.getLocalRolesUserCollection();
    return  localRolesUsers;
    }

    @Override
    public Collection<LocalRolesUser> getLocalRolesUsersByUser(Integer userId) {
    UserMaster user=em.find(UserMaster.class, userId);
    Collection<LocalRolesUser> localRolesUsers=user.getLocalRolesUserCollection();
    return  localRolesUsers;
     
    }

    @Override
    public void addLocalRoleUser(Integer roleId, Integer userId) {
 
        int flag=0;
      UserMaster user=em.find(UserMaster.class,userId);
      LocalRoles role=em.find(LocalRoles.class,roleId);
      int projectId=role.getProjectId().getProjectId();
      Collection<LocalRoles> roles=em.createNamedQuery("LocalRoles.findByProjectId").setParameter("projectId",projectId).getResultList();
      for(LocalRoles lr:roles)
      {
          for(LocalRolesUser lru:lr.getLocalRolesUserCollection())
          {
              if(lru.getUserMaster().getUserId()==userId)
              {
                  flag=1;
              }
          }
      }
      
      
      
      
      if(flag==0)
      {
       Collection<LocalRolesUser> l1=user.getLocalRolesUserCollection();
       Collection<LocalRolesUser> l2=role.getLocalRolesUserCollection();
      LocalRolesUser u=new LocalRolesUser(roleId,userId);
 
       u.setLocalRoles(role);
       u.setUserMaster(user);
        l1.add(u);
        l2.add(u);

        user.setLocalRolesUserCollection(l1);
        role.setLocalRolesUserCollection(l2);
 
        em.persist(u);
 
         em.merge(user);
            em.merge(role);
      }
    }


    @Override
    public   void removeLocalRoleUser(Integer id,Integer roleid,Integer userid){
        UserMaster user=em.find(UserMaster.class, userid);
        LocalRoles roles=em.find(LocalRoles.class,roleid);
        LocalRolesUser localRolesUser=(LocalRolesUser) em.createNamedQuery("LocalRolesUser.findById").setParameter("id",id).getResultList().stream().findFirst().orElse(null);
        Collection<LocalRolesUser> l1=user.getLocalRolesUserCollection();
        Collection<LocalRolesUser> l2=roles.getLocalRolesUserCollection();
        if(localRolesUser!=null)
        {
         if(l1.contains(localRolesUser))
         {
             l1.remove(localRolesUser);
         }
         if(l2.contains(localRolesUser))
         {
             l2.remove(localRolesUser);
         }
         
         user.setLocalRolesUserCollection(l1);
         roles.setLocalRolesUserCollection(l2);
         em.remove(localRolesUser);
         em.merge(user);
         em.merge(roles);
        }
    
    }


    @Override
    public void startAllTimers() {
        timerObj.startJobs();
    }

    @Override
    public void stopAllTimers() {
        timerObj.stopJobs();
    }

    @Override
    public void startTimer(String projectName) {
        timerObj.startJob(projectName);
    }

    @Override
    public void stopTimer(String projectName) {
        timerObj.stopJob(projectName);
    }
    
    @Override
    public Boolean getTimerState(String projectName) {
        return timerObj.getState(projectName);
    }
    
    
    @Override
    public void sendEmail(String to,String subject,String body) {
   
     String from="panktishah910@gmail.com";
     String password="MLAIGiveBest";
        Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          Session session = Session.getInstance(props,    
           new javax.mail.Authenticator() {    
           @Override
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject(subject);  
          
            try{
                  java.util.Date date = new java.util.Date();
                  SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
                  sendDate=obj.parse(new Timestamp(date.getTime()).toString());
                   }
            catch(ParseException ex)
            {
                System.err.println(ex.getMessage());
            }
           message.setSentDate(sendDate);
           message.setText(body);    
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    
          } catch (MessagingException e) {throw new RuntimeException(e);} 
    }

    @Override
    public String forgotpassword(String email) {
   UserMaster userMaster=(UserMaster) em.createNamedQuery("UserMaster.findByEmail").setParameter("email",email).getResultList().stream().findFirst().orElse(null);
    if(userMaster!=null)
    {
        GlobalRoles role=userMaster.getGlobalRoleId();
        Collection<UserMaster> users=role.getUserMasterCollection();
         users.remove(userMaster);
        String otp=new DecimalFormat("00000").format(new Random().nextInt(99999));
        sendEmail(email,"OTP for CI-CD", otp);
       
        
        userMaster.setOtp(otp);
        userMaster.setOTPSent(sendDate);
        users.add(userMaster);
        role.setUserMasterCollection(users);
        em.merge(userMaster);
        return "1";
        
    }
    else
    {
        return "0";
    }
    }

    @Override
    public String verifyOTP(String email, String otp) {
    //UserMaster userMaster=(UserMaster) em.createNamedQuery("UserMaster.findByverifyOTP").setParameter("email", email).setParameter("otp", otp).getResultList().stream().findFirst().orElse(null);
    UserMaster userMaster=(UserMaster) em.createNamedQuery("UserMaster.findByEmail").setParameter("email",email ).getResultList().stream().findFirst().orElse(null);
    String verify_otp=userMaster.getOtp();
    if(userMaster!=null)
    {
          java.util.Date date = new java.util.Date();
          SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        try {
            Date date1=sendDate;
            Date date2=obj.parse(new Timestamp(date.getTime()).toString());
            long time_diffrence=date2.getTime()-date1.getTime();
            long min_diffrence=(time_diffrence/(1000*60))%60;
            
            if(min_diffrence<=3 && userMaster.getOtp().equals(otp))
            {
               // userMaster.setOTPSent(null);
               // userMaster.setOtp(null);
              //  em.merge(userMaster);
                return "OTPSuccess";
            }
            else
            {
               //  userMaster.setOTPSent(null);
               // userMaster.setOtp(null);
                //em.merge(userMaster);
                return "TimeOut"; 
            }
            
            
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
          
          return "Check";
    }
    else
    {
        return "NotVaild";
    }
        
    }

    @Override
    public String resetPassword(String email, String password) {
    if(!email.isEmpty())
    {
        UserMaster userMaster=(UserMaster) em.createNamedQuery("UserMaster.findByEmail").setParameter("email",email).getResultList().stream().findFirst().orElse(null);
        GlobalRoles role=userMaster.getGlobalRoleId();
        Collection<UserMaster> users=role.getUserMasterCollection();
        users.remove(userMaster);
        userMaster.setPassword(password);
        userMaster.setOtp(null);
        userMaster.setOTPSent(null);
        users.add(userMaster);
        role.setUserMasterCollection(users);
        em.merge(userMaster);
        em.merge(role);
        return "1";
    }
    else
    {
        return "0";
    }
    }
    
    @Override
    public UserMaster loginUser(String email, String password) {
        UserMaster user=(UserMaster) em.createNamedQuery("UserMaster.findByLogin").setParameter("email",email ).setParameter("password",password).getResultList().stream().findFirst().orElse(null);
        return user;
    }

    @Override
    public UserMaster getUserByEmail(String email) {
    UserMaster user=(UserMaster) em.createNamedQuery("UserMaster.findByEmail").setParameter("email", email).getResultList().stream().findFirst().orElse(null);
    return user;
    }

    @Override
    public LocalRolesUser getLocalRolesUser(Integer userid,Integer localroleid) {
    LocalRolesUser l=(LocalRolesUser) em.createNamedQuery("LocalRolesUser.findByRoleUser").setParameter("localRoleId",localroleid).setParameter("userId",userid).getResultList().stream().findFirst().orElse(null);
    return l;
    
    }
    
    @Override
    public Collection<BuildMaster> getAllBuild ()
    {
        Collection<BuildMaster> bm = em.createNamedQuery("BuildMaster.findAll").getResultList();
        return bm;
    }
    @Override
    public Collection<BuildMaster> getBuildOfProject (Integer projectId)
    {
        Collection<BuildMaster> bm = em.createNamedQuery("BuildMaster.findByProjectId").setParameter("ProjectId", projectId).getResultList();
        return bm;
    }
    @Override
    public BuildMaster getBuild(Integer buildId)
    {
        BuildMaster bm = (BuildMaster)em.createNamedQuery("BuildMaster.findByBulidId").setParameter("buildId", buildId).getResultList().stream().findFirst().orElse(null);
        return bm;
    }

    @Override
    public void updateProject(Integer projectId, String projectName, String shellCommand, String timeInterval) {
        ProjectMaster pm = (ProjectMaster) em.find(ProjectMaster.class, projectId);
        pm.setProjectName(projectName);
        pm.setShellCommand(shellCommand);
        pm.setTimeInterval(timeInterval);
        em.merge(pm);
        em.flush();
    }

    @Override
    public void updateProject(Integer projectId, String projectName, String shellCommand, String timeInterval, String physicalPath) {
        ProjectMaster pm = (ProjectMaster) em.find(ProjectMaster.class, projectId);
        pm.setProjectName(projectName);
        pm.setShellCommand(shellCommand);
        pm.setTimeInterval(timeInterval);
        pm.getRepoId().setPysicalPath(physicalPath);
        em.merge(pm);
        em.flush();
    }

    @Override
    public void updateProject(Integer projectId, String projectName, String shellCommand, String timeInterval, String physicalPath, String gitUrl) {
        ProjectMaster pm = (ProjectMaster) em.find(ProjectMaster.class, projectId);
        pm.setProjectName(projectName);
        pm.setShellCommand(shellCommand);
        pm.setTimeInterval(timeInterval);
        pm.getRepoId().setPysicalPath(physicalPath);
        pm.getRepoId().getGitId().setRepoUrl(gitUrl);
        em.merge(pm);
        em.flush();
    }

    @Override
    public void updateProject(Integer projectId, String projectName, String shellCommand, String timeInterval, String physicalPath, String gitUrl, String username, String password, String user) {
        ProjectMaster pm = (ProjectMaster) em.find(ProjectMaster.class, projectId);
        pm.setProjectName(projectName);
        pm.setShellCommand(shellCommand);
        pm.setTimeInterval(timeInterval);
        pm.getRepoId().setPysicalPath(physicalPath);
        pm.getRepoId().getGitId().setRepoUrl(gitUrl);
        pm.getRepoId().getGitId().getCredentialId().setUsername(username);
        pm.getRepoId().getGitId().getCredentialId().setPassword(password);
        em.merge(pm);
        em.flush();
    }

    @Override
    public ProjectMaster getProject(Integer projectId) {
        ProjectMaster pm = (ProjectMaster)em.createNamedQuery("ProjectMaster.findByProjectId").setParameter("projectId", projectId).getResultList().stream().findFirst().orElse(null);
        return pm;
    }

    @Override
    public void buildProject(Integer projectId) {
        ProjectMaster p = (ProjectMaster)em.find(ProjectMaster.class, projectId);
        try {
            b.setProject(p).BuildSequance();
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
    }
    
    @Override
    public ProjectMaster checkLocalRights(Integer userid,Integer projectid) {
        UserMaster sendMaster=null;
        ProjectMaster sendProjectMaster=null;
     UserMaster user1=em.find(UserMaster.class,userid);
     ProjectMaster project1=em.find(ProjectMaster.class, projectid);
     Collection<LocalRoles> roles=project1.getLocalRolesCollection();
     for(LocalRoles lr:roles)
     {
         Collection<LocalRolesUser> localRolesUsers=lr.getLocalRolesUserCollection();
         for(LocalRolesUser lru:localRolesUsers)
         {
             if(lru.getUserMaster().getUserId()==userid)
             {
                 sendMaster=lru.getUserMaster();
                 sendProjectMaster=project1;
             }
         }
     }
     
     return  sendProjectMaster;                                                                                            
     
     
    }
    
    @Override
    public LocalRoles getLocalRoleByProjectAndUser(Integer projectId,Integer userId) {
        
        ProjectMaster p = em.find(ProjectMaster.class, projectId);
        UserMaster u = em.find(UserMaster.class, userId);
        LocalRoles sl = new LocalRoles();

        for (LocalRoles l: p.getLocalRolesCollection()){
            
            for(LocalRolesUser x:l.getLocalRolesUserCollection()){
                if(x.getUserMaster().equals(u))
                {
                    sl = l;

                }
            }
        }
        return sl;
    }
    
    @Override
    public void buildOnWebhook(String repoUrl)
    {
        Collection<GitMaster> gm = em.createNamedQuery("GitMaster.findAll").getResultList();
        for(GitMaster g:gm)
        {
            if(g.getRepoUrl().equals(repoUrl))
            {
                Collection<RepoMaster> rm = g.getRepoMasterCollection();
                for(RepoMaster repo:rm)
                {
                    Collection<ProjectMaster> pm =  repo.getProjectMasterCollection();
                    for(ProjectMaster p:pm)
                    {
                        this.buildProject(p.getProjectId());
                    }

                }
            }
        }
    }
    
    @Override
    public void removeProject(Integer projectId)
    {
        ProjectMaster p = em.find(ProjectMaster.class, projectId);
        timerObj.stopJob(p.getProjectName());
        if(p.getRepoId().getGitId() != null)
            em.remove(p.getRepoId().getGitId());
        if(p.getRepoId() != null)
            em.remove(p.getRepoId());
        if(p!=null)
            em.remove(p);
    }
    
}
