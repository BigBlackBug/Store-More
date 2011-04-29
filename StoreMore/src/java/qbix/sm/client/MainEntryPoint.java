package qbix.sm.client;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


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
