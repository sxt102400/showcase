package com.rats;


import org.springframework.stereotype.Component;

@Component
public class HelloBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void hello(){
        System.out.println("hello,"+name);
    }

}
