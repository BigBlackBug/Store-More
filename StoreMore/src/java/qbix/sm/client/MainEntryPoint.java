package qbix.sm.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import qbix.sm.client.beans.User;
import qbix.sm.client.services.SessionService;
import qbix.sm.client.services.SessionServiceAsync;


/**
 * Main entry point.
 *
 * @author iliax
 */

public class MainEntryPoint implements EntryPoint {
    public MainEntryPoint() {}

    public static final MainGinjector GINJECTOR=GWT.create(MainGinjector.class);

    public void onModuleLoad() {

        MainController mainController=GINJECTOR.getMainController();
        mainController.go(RootPanel.get());
    }
}
