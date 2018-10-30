package com.rats.activiti.identity;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;

public class UserManagerFactory  implements SessionFactory {

    @Override
    public Class<?> getSessionType() {
        return null;
    }

    @Override
    public Session openSession(CommandContext commandContext) {
        return null;
    }
}
