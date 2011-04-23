/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qbix.sm.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.LinkedList;
import org.workingonit.gwtbridge.GwtRemoteService;
import qbix.sm.client.beans.User;
import qbix.sm.server.dao.UserDao;

/**
 *
 * @author iliax
 */
@RemoteServiceRelativePath("userservice.rpc")
@GwtRemoteService("userservice.rpc")
public interface UserService extends RemoteService {
    LinkedList<User> getAll();

    User getById(Long userId);

    User getByName(String userName);

    void add(User newUser);

    void deleteById(Long userId);

    void deleteByName(String name);
}
