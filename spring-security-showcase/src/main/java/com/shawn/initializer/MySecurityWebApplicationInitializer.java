package com.shawn.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class MySecurityWebApplicationInitializer extends
        AbstractSecurityWebApplicationInitializer {
    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}