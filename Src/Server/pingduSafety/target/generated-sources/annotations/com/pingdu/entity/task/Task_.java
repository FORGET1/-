package com.pingdu.entity.task;

import com.pingdu.entity.enterprise.Enterprise;
import com.pingdu.entity.item.Item;
import com.pingdu.entity.point.Point;
import com.pingdu.entity.user.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(Task.class)
public class Task_ { 

    public static volatile SingularAttribute<Task, Short> exception;
    public static volatile SingularAttribute<Task, String> note;
    public static volatile SingularAttribute<Task, String> advDesc;
    public static volatile SingularAttribute<Task, String> issueTime;
    public static volatile SingularAttribute<Task, Enterprise> enterprise;
    public static volatile SingularAttribute<Task, String> imagePath;
    public static volatile SingularAttribute<Task, String> completeTime;
    public static volatile SingularAttribute<Task, String> verifyTime;
    public static volatile SingularAttribute<Task, String> uploadTime;
    public static volatile SingularAttribute<Task, Point> point;
    public static volatile SingularAttribute<Task, Short> isVerify;
    public static volatile SingularAttribute<Task, Integer> taskCode;
    public static volatile SingularAttribute<Task, String> expireDate;
    public static volatile SetAttribute<Task, Item> items;
    public static volatile SingularAttribute<Task, User> user;
    public static volatile SingularAttribute<Task, Short> isComplete;
    public static volatile SingularAttribute<Task, String> probDesc;

}