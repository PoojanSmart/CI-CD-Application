package entity;

import entity.LocalRolesUser;
import entity.ProjectMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-11T15:17:15")
@StaticMetamodel(LocalRoles.class)
public class LocalRoles_ { 

    public static volatile SingularAttribute<LocalRoles, String> role;
    public static volatile SingularAttribute<LocalRoles, Boolean> build;
    public static volatile SingularAttribute<LocalRoles, Boolean> buildHistory;
    public static volatile SingularAttribute<LocalRoles, Boolean> projectCreate;
    public static volatile CollectionAttribute<LocalRoles, LocalRolesUser> localRolesUserCollection;
    public static volatile SingularAttribute<LocalRoles, Boolean> projectExecute;
    public static volatile SingularAttribute<LocalRoles, Boolean> projectRemove;
    public static volatile SingularAttribute<LocalRoles, Integer> id;
    public static volatile SingularAttribute<LocalRoles, ProjectMaster> projectId;

}