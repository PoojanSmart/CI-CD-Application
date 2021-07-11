package entity;

import entity.BuildMaster;
import entity.LocalRoles;
import entity.RepoMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-11T15:17:15")
@StaticMetamodel(ProjectMaster.class)
public class ProjectMaster_ { 

    public static volatile SingularAttribute<ProjectMaster, String> shellCommand;
    public static volatile SingularAttribute<ProjectMaster, RepoMaster> repoId;
    public static volatile CollectionAttribute<ProjectMaster, BuildMaster> buildMasterCollection;
    public static volatile SingularAttribute<ProjectMaster, String> timeInterval;
    public static volatile CollectionAttribute<ProjectMaster, LocalRoles> localRolesCollection;
    public static volatile SingularAttribute<ProjectMaster, String> projectName;
    public static volatile SingularAttribute<ProjectMaster, Integer> projectId;

}