package entity;

import entity.ProjectMaster;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-07-11T15:17:15")
@StaticMetamodel(BuildMaster.class)
public class BuildMaster_ { 

    public static volatile SingularAttribute<BuildMaster, Integer> bulidId;
    public static volatile SingularAttribute<BuildMaster, String> stage;
    public static volatile SingularAttribute<BuildMaster, Date> buildTime;
    public static volatile SingularAttribute<BuildMaster, String> build;
    public static volatile SingularAttribute<BuildMaster, Boolean> pass;
    public static volatile SingularAttribute<BuildMaster, ProjectMaster> projectId;

}