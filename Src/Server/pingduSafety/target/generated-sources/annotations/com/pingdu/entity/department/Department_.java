package com.pingdu.entity.department;

import com.pingdu.entity.user.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, String> deptName;
    public static volatile SingularAttribute<Department, String> note;
    public static volatile SingularAttribute<Department, Integer> deptLevel;
    public static volatile SingularAttribute<Department, Integer> parentCode;
    public static volatile SingularAttribute<Department, String> contact;
    public static volatile SingularAttribute<Department, String> contactPhone;
    public static volatile SingularAttribute<Department, String> deptAddr;
    public static volatile SingularAttribute<Department, Integer> deptCode;
    public static volatile SetAttribute<Department, User> users;
    public static volatile SingularAttribute<Department, String> deptPhone;

}