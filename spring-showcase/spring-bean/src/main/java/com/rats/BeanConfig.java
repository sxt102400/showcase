package com.rats;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(name="helloBean")
    public HelloBean helloBean() {
        HelloBean helloBean= new HelloBean();
        helloBean.setName("rats");
        return helloBean;
    }
}
