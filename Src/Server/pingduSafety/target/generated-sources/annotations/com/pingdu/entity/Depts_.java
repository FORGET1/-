package com.pingdu.entity;

import com.pingdu.entity.Students;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(Depts.class)
public class Depts_ { 

    public static volatile SingularAttribute<Depts, String> deptName;
    public static volatile SingularAttribute<Depts, Integer> deptId;
    public static volatile SetAttribute<Depts, Students> students;

}