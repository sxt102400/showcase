package com.rats.orm.annotation;

public @interface SafeDelete {

    String name() default "is_deleted";

    String flag() default "1"; //删除后的状态为1

}
