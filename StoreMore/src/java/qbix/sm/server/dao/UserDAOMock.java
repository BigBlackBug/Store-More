/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.User;

/**
 *
 * @author BigBlackBug
 */
public class UserDAOMock implements UserDao
{
    private static LinkedList<User> users = new LinkedList<User>();

    public UserDAOMock()
    {
        
    }

    public LinkedList<User> getAll()
    {
        return users;
    }

    public User getById(Long userId)
    {
        User user = new User();

        for (User u : users)
            if (u.getUserId() == userId)
            {
                user = u;
                break;
            }
        return user;
    }

    public User getByName(String userName)
    {
        User user = new User();

        for (User u : users)
            if (u.getName().equals(userName))
            {
                user = u;
                break;
            }
        return user;
    }

    public void add(User newUser)
    {
        users.add(newUser);
    }

    public void deleteById(Long userId)
    {
        for(User u:users)
            if(u.getUserId()==userId)
                users.remove(u);
    }

    public void deleteByName(String name)
    {
        for(User u:users)
            if(u.getName().equals(name))
                users.remove(u);
    }
}
