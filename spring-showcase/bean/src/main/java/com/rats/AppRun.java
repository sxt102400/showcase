package com.rats;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppRun {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloBean helloBean = (HelloBean) applicationContext.getBean("helloBean");
        helloBean.hello();
    }
}
