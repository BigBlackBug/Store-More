package qbix.sm.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import qbix.sm.client.beans.User;
import qbix.sm.client.events.AbstractAsyncCallBack;
import qbix.sm.client.events.ShowAccoutPageEvent;
import qbix.sm.client.services.SessionService;
import qbix.sm.client.services.SessionServiceAsync;
import qbix.sm.client.services.UserServiceAsync;

/**
 *
 * @author iliax
 */

public class HeaderPanel extends HorizontalPanel{

    Button userNameButton=new Button();
    TextField<String> userSearchField=new TextField<String>();
    Button userSeachButton=new Button("search!");
    Button logOffButton=new Button("logOff!");

    User currentUser;

    FormPanel logInForm=new FormPanel();
        TextField<String> userNameField=new TextField<String>();
        TextField<String> userPasswordField=new TextField<String>();
        Button logInButton=new Button("logIn!");

    //injected
    private EventBus eventBus;

    //injected
    private UserServiceAsync userService;

private SessionServiceAsync sessionService=GWT.create(SessionService.class);

    @Inject
    public HeaderPanel(EventBus eventBus, UserServiceAsync userService) {
        this.eventBus=eventBus;
        this.userService=userService;

        add(userNameButton);

        initUserSearch();
        add(userSearchField);
        add(userSeachButton);

        initLogInPanel();
        add(logInForm);

        add(logOffButton);

        sessionService.getUserFromSession(new AbstractAsyncCallBack<User>() {
            @Override
            public void handleFailture(Throwable caugh) {}
            @Override
            public void handleSuccess(User result) {
                if (result!=null)
                    setAuthMode(result);
                else
                    setGuestMode();
            }
        });
      }

    private void  goToAccountPage(User user){
        eventBus.fireEvent(new ShowAccoutPageEvent(user));
    }

    private void initLogInPanel(){
        //logInButton.addListener(null, null);
        logInForm.setCollapsible(true);
        logInForm.collapse();
        logInForm.setTitleCollapse(true);
        logInForm.setHeading("Log In Form");
        userNameField.setFieldLabel("nick");
        userPasswordField.setFieldLabel("passw");
        userPasswordField.setPassword(true);
        logInForm.add(userNameField);
        logInForm.add(userPasswordField);
        logInForm.add(logInButton);
    }

    private void initUserSearch() {
        //userSeachButton.addListener(null, null);
    }

    public void setAuthMode(User user){
        currentUser=user;
        userNameButton.setText(user.getName());
        userNameButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                eventBus.fireEvent(new ShowAccoutPageEvent(currentUser));
            }
        });
        logInForm.setVisible(false);
        logOffButton.setVisible(true);
        logOffButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
               sessionService.invalidate(new AbstractAsyncCallBack<Void>() {
                    @Override
                    public void handleFailture(Throwable caugh) {}
                    @Override
                    public void handleSuccess(Void result) {
                        setGuestMode();
                    }
                });
            }
        });
    }

    public void setGuestMode(){
        currentUser=null;
        userNameButton.setText("UnAuthorizedUser");
        userNameButton.setEnabled(false);
        logInForm.setVisible(true);
        logOffButton.setVisible(false);
    }
}