
package com.rats;

import com.rats.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class Main {

    public static void testAdd(){

        //读取hibernate.cfg.xml文件
        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        User user = new User();
        user.setUsername("hanb");
        user.setSex("1");
        user.setBirthday(new Date());
        session.save(user);

        tx.commit();
        session.close();
    }

    public static void main(String[] args) {
        testAdd();
    }
}