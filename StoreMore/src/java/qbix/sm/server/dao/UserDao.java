package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */
public interface  UserDao {

    LinkedList<User> getAll();

    User getById(Long userId);

    User getByName(String userName);

    void add(User newUser);
}
