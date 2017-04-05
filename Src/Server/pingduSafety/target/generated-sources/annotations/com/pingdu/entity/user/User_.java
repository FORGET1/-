package com.pingdu.entity.user;

import com.pingdu.entity.department.Department;
import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.role.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> note;
    public static volatile SingularAttribute<User, String> salt;
    public static volatile SingularAttribute<User, Role> role;
    public static volatile SingularAttribute<User, String> gender;
    public static volatile SingularAttribute<User, Enterprise> enterprise;
    public static volatile SingularAttribute<User, String> birthDate;
    public static volatile SingularAttribute<User, String> userCode;
    public static volatile SingularAttribute<User, String> mac;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> phone;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> id;
    public static volatile SingularAttribute<User, Department> department;

}