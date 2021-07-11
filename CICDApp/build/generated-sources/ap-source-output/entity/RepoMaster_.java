package entity;

import entity.GitMaster;
import entity.ProjectMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-11T15:17:15")
@StaticMetamodel(RepoMaster.class)
public class RepoMaster_ { 

    public static volatile SingularAttribute<RepoMaster, String> pysicalPath;
    public static volatile CollectionAttribute<RepoMaster, ProjectMaster> projectMasterCollection;
    public static volatile SingularAttribute<RepoMaster, Integer> repoId;
    public static volatile SingularAttribute<RepoMaster, GitMaster> gitId;

}