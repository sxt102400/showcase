package com.rats.showcase.identity;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;

import java.util.List;
import java.util.Map;

public class IUserManager implements UserEntityManager, Session {

    @Override
    public void flush() {

    }

    @Override
    public void close() {

    }

    @Override
    public User createNewUser(String s) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl userQuery, Page page) {
        return null;
    }

    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl userQuery) {
        return 0;
    }

    @Override
    public List<Group> findGroupsByUser(String s) {
        return null;
    }

    @Override
    public UserQuery createNewUserQuery() {
        return null;
    }

    @Override
    public Boolean checkPassword(String s, String s1) {
        return null;
    }

    @Override
    public List<User> findUsersByNativeQuery(Map<String, Object> map, int i, int i1) {
        return null;
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> map) {
        return 0;
    }

    @Override
    public boolean isNewUser(User user) {
        return false;
    }

    @Override
    public Picture getUserPicture(String s) {
        return null;
    }

    @Override
    public void setUserPicture(String s, Picture picture) {

    }

    @Override
    public void deletePicture(User user) {

    }

    @Override
    public UserEntity create() {
        return null;
    }

    @Override
    public UserEntity findById(String s) {
        return null;
    }

    @Override
    public void insert(UserEntity entity) {

    }

    @Override
    public void insert(UserEntity entity, boolean b) {

    }

    @Override
    public UserEntity update(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity entity, boolean b) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(UserEntity entity) {

    }

    @Override
    public void delete(UserEntity entity, boolean b) {

    }
}
