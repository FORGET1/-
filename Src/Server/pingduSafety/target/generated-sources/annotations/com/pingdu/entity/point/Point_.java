package com.pingdu.entity.point;

import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.task.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(Point.class)
public class Point_ { 

    public static volatile SingularAttribute<Point, String> note;
    public static volatile SingularAttribute<Point, Integer> pointCode;
    public static volatile SingularAttribute<Point, String> pointInfo;
    public static volatile SingularAttribute<Point, Enterprise> enterprise;
    public static volatile SingularAttribute<Point, String> pointName;
    public static volatile SingularAttribute<Point, Double> longtitude;
    public static volatile SingularAttribute<Point, Integer> patrolCircle;
    public static volatile SingularAttribute<Point, String> NFCCode;
    public static volatile SingularAttribute<Point, String> pointPerson;
    public static volatile SingularAttribute<Point, String> safetyTips;
    public static volatile SingularAttribute<Point, String> pointAddr;
    public static volatile SingularAttribute<Point, String> pointPersonTel;
    public static volatile SetAttribute<Point, Item> items;
    public static volatile SingularAttribute<Point, Double> lantitude;
    public static volatile SetAttribute<Point, Task> tasks;

}