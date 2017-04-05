package com.pingdu.entity;

import com.pingdu.entity.Depts;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(Students.class)
public class Students_ { 

    public static volatile SingularAttribute<Students, Date> resTime;
    public static volatile SingularAttribute<Students, String> stuName;
    public static volatile SingularAttribute<Students, Long> stuNo;
    public static volatile SingularAttribute<Students, Depts> depts;

}