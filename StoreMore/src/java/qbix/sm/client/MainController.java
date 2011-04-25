package qbix.sm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import java.util.LinkedList;
import qbix.sm.client.beans.SmFile;

import qbix.sm.client.beans.User;
import qbix.sm.client.events.AbstractAsyncCallBack;
import qbix.sm.client.events.ShowAccoutPageEvent;
import qbix.sm.client.presenters.AccountOwnerPagePresenter;
import qbix.sm.client.presenters.AccountPagePresenter;
import qbix.sm.client.presenters.Presenter;
import qbix.sm.client.services.FCService;
import qbix.sm.client.services.FCServiceAsync;
import qbix.sm.client.services.SessionService;
import qbix.sm.client.services.SessionServiceAsync;
import qbix.sm.client.services.UserServiceAsync;

/**
 *
 * @author iliax
 */
public class MainController implements ValueChangeHandler<String>, Presenter{
    //injected
    private EventBus eventBus;
    //injected
    private UserServiceAsync userService;
    private SessionServiceAsync sessionService = GWT.create(SessionService.class);
    private HasWidgets container;
    //для запоминания предыдущего состояния истории(может понадобится)
    String tempHistoryItem;
    Presenter currentPresenter;

    User targetPageOwner;

    @Inject  
    public MainController(EventBus eventBus , UserServiceAsync userService) {
        this.eventBus = eventBus;
        this.userService = userService;
        bind();
    }

    private void bind(){
        History.addValueChangeHandler(this);

        //привязываем все ивенты здесь:
     
        eventBus.addHandler(ShowAccoutPageEvent.TYPE, new ShowAccoutPageEvent.Event_Handler() {
            public void onShowAccountPageEvent(ShowAccoutPageEvent event) {
                targetPageOwner=event.getAccountOwner();
                History.newItem(targetPageOwner.getName());
            }
        });
    }

    public void go(final HasWidgets container){
        this.container = container;

        if ("".equals(History.getToken()))
            History.newItem("main");
        else
            History.fireCurrentHistoryState();
    }

    public void onValueChange(final ValueChangeEvent<String> event){
        final String token = event.getValue();
        if (token != null){

            if ("main".equals(token)){
                postOnValueChange(event);
                return;
            }

            if ("".equals(token)){
                History.newItem("main");
                return;
            }
            

            userService.getByName(token, new AbstractAsyncCallBack<User>(){
                @Override
                public void handleFailture(Throwable caugh){}
                @Override
                public void handleSuccess(User result){
                    if (result == null)
                        History.newItem("main");  //если ввели бред
                    else{
                        targetPageOwner = result;
                        postOnValueChange(event);
                    }
                }
            });
        }
    }

    public void postOnValueChange(ValueChangeEvent<String> event){
        final String token = event.getValue();

        if (token != null)
            sessionService.getUserFromSession(new AbstractAsyncCallBack<User>(){
                @Override
                public void handleFailture(Throwable caugh){
                    //session access failed presenter
                    //currentPresenter.go(container);
                }

                @Override
                public void handleSuccess(User userInSession){
                    if (userInSession != null)
                        //for page owners:
                        if (token.equals("main")){
                            AccountOwnerPagePresenter aopp =
                                    MainEntryPoint.GINJECTOR.getAccountOwnerPagePresenter();
                            aopp.setOwner(userInSession);
                            currentPresenter = aopp;
                        } else if (targetPageOwner.equals(userInSession)){
                            AccountOwnerPagePresenter aopp =
                                    MainEntryPoint.GINJECTOR.getAccountOwnerPagePresenter();
                            aopp.setOwner(userInSession);
                            targetPageOwner = null;
                            currentPresenter = aopp;
                        } else{
                            AccountPagePresenter app =
                                    MainEntryPoint.GINJECTOR.getAccountPagePresenter();
                            app.setOwner(targetPageOwner);
                            targetPageOwner = null;
                            currentPresenter = app;
                        }
                    else
                        //for page guests:
                        if (token.equals("main"))
                            currentPresenter = MainEntryPoint.GINJECTOR.getMainPagePresenter();
                        else if (targetPageOwner != null){
                            AccountPagePresenter app =
                                    MainEntryPoint.GINJECTOR.getAccountPagePresenter();
                            app.setOwner(targetPageOwner);
                            targetPageOwner = null;
                            currentPresenter = app;
                        }

                    if (currentPresenter != null)
                        currentPresenter.go(container);
                }
            });
    }
}
