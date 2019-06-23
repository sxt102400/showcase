
package com.rats;

import com.rats.entity.User;

import javax.persistence.*;
import java.util.Date;

public class Main {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");

    public static void testAdd(){

        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();

        User user = new User();
        user.setUsername("hanb");
        user.setSex("1");
        user.setBirthday(new Date());
        entityManager.persist(user);

        txn.commit();

    }

    public static void main(String[] args) {
        testAdd();
    }
}