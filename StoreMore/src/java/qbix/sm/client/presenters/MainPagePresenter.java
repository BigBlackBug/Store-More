package qbix.sm.client.presenters;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;

/**
 *
 * @author iliax
 */
public class MainPagePresenter implements  Presenter{

    public void go(HasWidgets container) {container.clear();
        container.add(new Label("MainPresenter"));
    }

}
