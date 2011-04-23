/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qbix.sm.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.LinkedList;
import qbix.sm.client.beans.User;

import qbix.sm.client.services.UserService;
import qbix.sm.server.dao.UserDao;

/**
 *
 * @author iliax
 */

public class UserServiceImpl extends RemoteServiceServlet implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public LinkedList<User> getAll() {
        return userDao.getAll();
    }

    public User getById(Long userId) {
        return userDao.getById(userId);
    }

    public User getByName(String userName) {
        return userDao.getByName(userName);
    }

    public void add(User newUser) {
        userDao.add(newUser);
    }

    public void deleteById(Long userId) {
        userDao.deleteById(userId);
    }

    public void deleteByName(String name) {
        userDao.deleteByName(name);
    }

    
}
