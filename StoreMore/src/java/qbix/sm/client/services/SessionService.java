package qbix.sm.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */

@RemoteServiceRelativePath("services/sessionservice")
public interface SessionService extends RemoteService {

    public void addUserToSession(User user);

    public boolean isAuthenticated();

    public void invalidate();

    public User getUserFromSession();
}
