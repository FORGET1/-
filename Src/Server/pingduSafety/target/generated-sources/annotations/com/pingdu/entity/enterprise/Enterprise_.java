package com.pingdu.entity.enterprise;

import com.pingdu.entity.entType.EntType;
import com.pingdu.entity.task.Task;
import com.pingdu.entity.user.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(Enterprise.class)
public class Enterprise_ { 

    public static volatile SingularAttribute<Enterprise, String> note;
    public static volatile SingularAttribute<Enterprise, String> principle;
    public static volatile SingularAttribute<Enterprise, String> entName;
    public static volatile SingularAttribute<Enterprise, Integer> entLevel;
    public static volatile SingularAttribute<Enterprise, String> entTel;
    public static volatile SetAttribute<Enterprise, User> users;
    public static volatile SingularAttribute<Enterprise, EntType> entType;
    public static volatile SingularAttribute<Enterprise, String> entAddr;
    public static volatile SetAttribute<Enterprise, Task> task;
    public static volatile SingularAttribute<Enterprise, Integer> entCode;
    public static volatile SingularAttribute<Enterprise, Integer> parentCode;
    public static volatile SingularAttribute<Enterprise, String> prinPhone;
    public static volatile SingularAttribute<Enterprise, Integer> deptCode;

}