package com.pingdu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ZhongCB
 * @date 2016年09月10日 14:55
 * @description
 */
public class AutoTaskTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext-mvc.xml");
    }
}