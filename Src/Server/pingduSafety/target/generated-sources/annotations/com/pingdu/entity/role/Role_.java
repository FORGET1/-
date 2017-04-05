package com.pingdu.entity.role;

import com.pingdu.entity.user.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile SingularAttribute<Role, String> note;
    public static volatile SingularAttribute<Role, Integer> permissions;
    public static volatile SingularAttribute<Role, Integer> roleCode;
    public static volatile SingularAttribute<Role, String> roleName;
    public static volatile SingularAttribute<Role, Short> state;
    public static volatile SetAttribute<Role, User> users;

}