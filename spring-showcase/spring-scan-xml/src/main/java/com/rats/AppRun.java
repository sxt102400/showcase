package com.rats;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AppRun {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext  applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanConfig.class);
        applicationContext.refresh();

        HelloBean helloBean = (HelloBean) applicationContext.getBean("helloBean");
        helloBean.setName("rats");
        helloBean.hello();
    }
}
