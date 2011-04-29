package qbix.sm.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import javax.servlet.http.HttpSession;
import qbix.sm.client.beans.User;

import qbix.sm.client.services.SessionService;

/**
 *
 * @author iliax
 */
public class SessionServiceImpl extends RemoteServiceServlet implements SessionService {

    public void addUserToSession(User user) {
        HttpSession session=getThreadLocalRequest().getSession(true);
        if(session.getAttribute("user")==null)
            session.setAttribute("user", user);
    }

    public boolean isAuthenticated(){
        return getThreadLocalRequest().getSession(true).getAttribute("user")!=null;
    }

    public void invalidate() {
           // getThreadLocalRequest().getSession().invalidate();
            getThreadLocalRequest().getSession().removeAttribute("user");
    }

    public User getUserFromSession() {
        if(getThreadLocalRequest().getSession(true).getAttribute("user")!=null)
            return (User)getThreadLocalRequest().getSession(true).getAttribute("user");
        else
            return null;
    }
}
