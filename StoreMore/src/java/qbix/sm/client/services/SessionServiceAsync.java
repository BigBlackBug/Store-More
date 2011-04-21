/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qbix.sm.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */
public interface SessionServiceAsync {

    public void addUserToSession(User user, AsyncCallback<Void> asyncCallback);

    public void isAuthenticated(AsyncCallback<java.lang.Boolean> asyncCallback);

    public void invalidate(AsyncCallback<Void> asyncCallback);

    public void getUserFromSession(AsyncCallback<User> asyncCallback);

}
