package qbix.sm.client.presenters;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */
public class AccountPagePresenter implements Presenter{

    User pageOwner;

    public void go(HasWidgets container) {container.clear();
        container.add(new Label(pageOwner.getName()+"'s acc page presenter"));
    }

    public void setOwner(User userInSession) {
        pageOwner=userInSession;
    }

}
