package com.pingdu.entity.notice;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2017-03-29T21:39:13")
@StaticMetamodel(NoticeRead.class)
public class NoticeRead_ { 

    public static volatile SingularAttribute<NoticeRead, String> note;
    public static volatile SingularAttribute<NoticeRead, Integer> noticeCode;
    public static volatile SingularAttribute<NoticeRead, Integer> noticeReadCode;
    public static volatile SingularAttribute<NoticeRead, String> readDate;
    public static volatile SingularAttribute<NoticeRead, Short> isRead;
    public static volatile SingularAttribute<NoticeRead, Integer> userCode;

}