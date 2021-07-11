package entity;

import entity.GitMaster;
import entity.UserMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-11T15:17:15")
@StaticMetamodel(RepoCredential.class)
public class RepoCredential_ { 

    public static volatile SingularAttribute<RepoCredential, String> password;
    public static volatile CollectionAttribute<RepoCredential, GitMaster> gitMasterCollection;
    public static volatile SingularAttribute<RepoCredential, Integer> credentialId;
    public static volatile SingularAttribute<RepoCredential, UserMaster> userId;
    public static volatile SingularAttribute<RepoCredential, String> username;

}