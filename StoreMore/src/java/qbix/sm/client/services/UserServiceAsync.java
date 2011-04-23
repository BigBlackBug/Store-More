/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qbix.sm.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.LinkedList;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */
public interface UserServiceAsync {

    public void getAll(AsyncCallback<LinkedList<User>> asyncCallback);

    public void getById(Long userId, AsyncCallback<User> asyncCallback);

    public void getByName(String userName, AsyncCallback<User> asyncCallback);

    public void add(User newUser, AsyncCallback<Void> asyncCallback);

    public void deleteById(Long userId, AsyncCallback<Void> asyncCallback);

    public void deleteByName(String name, AsyncCallback<Void> asyncCallback);
    
}
