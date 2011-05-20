package qbix.sm.client;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.Popup;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import qbix.sm.client.beans.User;
import qbix.sm.client.events.AbstractAsyncCallBack;
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
    Button userNameButton = new Button();
    TextField<String> userSearchField = new TextField<String>();
    Button userSeachButton = new Button("search!");
    Button logOffButton = new Button("logOff!");
    User currentUser;
    FormPanel logInForm = new FormPanel();
    Popup logInPopup=new Popup();
    Button logInPopupButton=new Button("LogIn!");
    TextField<String> userNameField = new TextField<String>();
    TextField<String> userPasswordField = new TextField<String>();
    Button logInButton = new Button("logIn!");
    //injected
    private EventBus eventBus;
    //injected
    private UserServiceAsync userService;

    private SessionServiceAsync sessionService = GWT.create(SessionService.class);

    UploaderPanel uploaderPanel=new UploaderPanel();


    @Inject
    public HeaderPanel(EventBus eventBus, UserServiceAsync userService){
        this.eventBus = eventBus;
        this.userService = userService;

        add(userNameButton);

        initUserSearch();
        userSearchField.setFieldLabel("Search");
        userSearchField.setTitle("User Search");
        add(userSearchField);
        add(userSeachButton);

        initLogInPanel();
        //add(logInForm);
        add(logInPopupButton);

        add(logOffButton);

        add(uploaderPanel);

        sessionService.getUserFromSession(new AbstractAsyncCallBack<User>() {
            @Override
            public void handleFailure(Throwable caugh){}
            @Override
            public void handleSuccess(User result){
                if (result != null)
                    setAuthMode(result);
                else
                    setGuestMode();
            }
        });

        
    }

    private void goToAccountPage(User user){
        eventBus.fireEvent(new ShowAccoutPageEvent(user));
    }

    private void initLogInPanel(){
        logInButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                userService.getByName(userNameField.getValue().trim(), new AbstractAsyncCallBack<User>() {
                    @Override
                    public void handleFailure(Throwable caugh) {}
                    @Override
                    public void handleSuccess(final User result) {
                        if(result!=null)
                            if(result.getPassword().equals(userPasswordField.getValue())==true){
                                sessionService.addUserToSession(result, new AbstractAsyncCallBack<Void>() {
                                    @Override
                                    public void handleFailure(Throwable caugh) {}
                                    @Override
                                    public void handleSuccess(Void voidres) {
                                        HeaderPanel.this.setAuthMode(result);
                                        logInPopup.hide();
                                        eventBus.fireEvent(new ShowAccoutPageEvent(result));
                                    }
                                });
                            }
                            else
                                Window.alert("wrong data!");
                        else
                          Window.alert("wrong data!");

                    }
                });
            }
        });
        
        logInForm.setTitleCollapse(true);
        logInForm.setHeading("Log In Form");
        userNameField.setFieldLabel("nick");
        userNameField.setValue("");
        userPasswordField.setFieldLabel("passw");
        userPasswordField.setValue("");
        userPasswordField.setPassword(true);
        logInForm.add(userNameField);
        logInForm.add(userPasswordField);
        logInForm.add(logInButton);

        logInPopup.add(logInForm);
        logInPopup.setAnimate(true);

        logInPopupButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                logInPopup.show(logInPopupButton);
            }
        });
    }

    private void initUserSearch() {
        userSeachButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                userService.getByName(userSearchField.getValue().trim(), new AbstractAsyncCallBack<User>() {
                    @Override
                    public void handleFailure(Throwable caugh) {}
                    @Override
                    public void handleSuccess(User result) {
                        if(result!=null)
                            goToAccountPage(result);
                        else
                            Window.alert("no users with this name!");
                    }
                });
            }
        });
    }

    public void setAuthMode(User user){
        currentUser=user;
        uploaderPanel.setVisible(true);
        logInPopupButton.setVisible(false);
        userNameButton.setText(user.getName());
        userNameButton.addSelectionListener(new SelectionListener<ButtonEvent>(){
            @Override
            public void componentSelected(ButtonEvent ce){
                eventBus.fireEvent(new ShowAccoutPageEvent(currentUser));
            }
        });

        logOffButton.setVisible(true);
        logOffButton.addSelectionListener(new SelectionListener<ButtonEvent>(){
            @Override
            public void componentSelected(ButtonEvent ce){
                sessionService.invalidate(new AbstractAsyncCallBack<Void>(){
                    @Override
                    public void handleFailure(Throwable caugh){}
                    @Override
                    public void handleSuccess(Void result) {
                       /// goToAccountPage(currentUser);
                        ///uploaderPanel.cancel();
                        History.newItem("main");
                        setGuestMode();
                    }
                });
            }
        });
        userNameButton.setEnabled(true);
    }

    public void setGuestMode(){
        uploaderPanel.setVisible(false);
        currentUser = null;
        userNameButton.setText("UnAuthorizedUser");
        userNameButton.setEnabled(false);
        logInPopupButton.setVisible(true);
        logOffButton.setVisible(false);
        userNameField.setValue("");
        userPasswordField.setValue("");
    }
}
