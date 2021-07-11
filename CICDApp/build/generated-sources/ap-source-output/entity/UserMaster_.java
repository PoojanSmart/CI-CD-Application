package entity;

import entity.GlobalRoles;
import entity.LocalRolesUser;
import entity.RepoCredential;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-11T15:17:15")
@StaticMetamodel(UserMaster.class)
public class UserMaster_ { 

    public static volatile SingularAttribute<UserMaster, String> password;
    public static volatile SingularAttribute<UserMaster, Boolean> receivesEmail;
    public static volatile CollectionAttribute<UserMaster, LocalRolesUser> localRolesUserCollection;
    public static volatile SingularAttribute<UserMaster, String> name;
    public static volatile CollectionAttribute<UserMaster, RepoCredential> repoCredentialCollection;
    public static volatile SingularAttribute<UserMaster, String> otp;
    public static volatile SingularAttribute<UserMaster, Date> oTPSent;
    public static volatile SingularAttribute<UserMaster, GlobalRoles> globalRoleId;
    public static volatile SingularAttribute<UserMaster, Integer> userId;
    public static volatile SingularAttribute<UserMaster, String> email;

}