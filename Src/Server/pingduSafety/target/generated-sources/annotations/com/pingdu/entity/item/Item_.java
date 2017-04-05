package com.pingdu.entity.item;

import com.pingdu.entity.point.Point;
import com.pingdu.entity.task.Task;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, String> note;
    public static volatile SingularAttribute<Item, Integer> itemCode;
    public static volatile SingularAttribute<Item, String> itemInfo;
    public static volatile SetAttribute<Item, Task> tasks;
    public static volatile SetAttribute<Item, Point> points;

}