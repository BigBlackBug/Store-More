package qbix.sm.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import qbix.sm.client.beans.User;
import qbix.sm.client.services.SessionService;
import qbix.sm.client.services.SessionServiceAsync;
import qbix.sm.client.services.UserServiceAsync;


/**
 * Main entry point.
 *
 * @author iliax
 */

public class MainEntryPoint implements EntryPoint {
    public MainEntryPoint() {}

    public static final MainGinjector GINJECTOR=GWT.create(MainGinjector.class);

    public void onModuleLoad() {
        VerticalPanel verticalPanel=new VerticalPanel();
        HeaderPanel headerPanel=GINJECTOR.getHeaderPanel();
        verticalPanel.add(headerPanel);
        HorizontalPanel horizontalPanel=new HorizontalPanel();
        verticalPanel.add(horizontalPanel);

        RootPanel.get().add(verticalPanel);

        MainController mainController=GINJECTOR.getMainController();
        mainController.go(horizontalPanel);
    }
}
