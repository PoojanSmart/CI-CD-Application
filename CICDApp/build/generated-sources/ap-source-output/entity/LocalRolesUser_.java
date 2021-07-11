package entity;

import entity.LocalRoles;
import entity.LocalRolesUserPK;
import entity.UserMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-11T15:17:15")
@StaticMetamodel(LocalRolesUser.class)
public class LocalRolesUser_ { 

    public static volatile SingularAttribute<LocalRolesUser, UserMaster> userMaster;
    public static volatile SingularAttribute<LocalRolesUser, LocalRoles> localRoles;
    public static volatile SingularAttribute<LocalRolesUser, LocalRolesUserPK> localRolesUserPK;

}