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
        users.add(new User(new Long(1), "iliax", "fdsf", "dhghr", "GDF"));
        users.add(new User(new Long(2), "ili123ax", "fWERdsf", "dWERhghr", "GDWERF"));
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
        if(userName.equals("iliax"))
            return new User(new Long(11), "iliax", "123", "123", "123");

        return null;
//        User user = new User();
//
//        for (User u : users)
//            if (u.getName().equals(userName))
//            {
//                user = u;
//                break;
//            }
//        return user;
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
