package entity;

import entity.UserMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-11T15:17:15")
@StaticMetamodel(GlobalRoles.class)
public class GlobalRoles_ { 

    public static volatile SingularAttribute<GlobalRoles, String> role;
    public static volatile SingularAttribute<GlobalRoles, Boolean> buildHistory;
    public static volatile SingularAttribute<GlobalRoles, Boolean> projectCreate;
    public static volatile CollectionAttribute<GlobalRoles, UserMaster> userMasterCollection;
    public static volatile SingularAttribute<GlobalRoles, Boolean> projectExecute;
    public static volatile SingularAttribute<GlobalRoles, Boolean> projectRemove;
    public static volatile SingularAttribute<GlobalRoles, Integer> id;
    public static volatile SingularAttribute<GlobalRoles, Boolean> credentialCreate;
    public static volatile SingularAttribute<GlobalRoles, Boolean> credentialDelete;
    public static volatile SingularAttribute<GlobalRoles, Boolean> credentialView;
    public static volatile SingularAttribute<GlobalRoles, Boolean> bulid;

}