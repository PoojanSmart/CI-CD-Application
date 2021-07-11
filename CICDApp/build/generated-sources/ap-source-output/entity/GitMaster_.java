package entity;

import entity.RepoCredential;
import entity.RepoMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-11T15:17:15")
@StaticMetamodel(GitMaster.class)
public class GitMaster_ { 

    public static volatile SingularAttribute<GitMaster, String> repoUrl;
    public static volatile SingularAttribute<GitMaster, Integer> gitId;
    public static volatile SingularAttribute<GitMaster, RepoCredential> credentialId;
    public static volatile CollectionAttribute<GitMaster, RepoMaster> repoMasterCollection;

}