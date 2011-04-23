package qbix.sm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import qbix.sm.client.beans.User;
import qbix.sm.client.events.AbstractAsyncCallBack;
import qbix.sm.client.events.ShowAccoutPageEvent;
import qbix.sm.client.presenters.Presenter;
import qbix.sm.client.services.SessionService;
import qbix.sm.client.services.SessionServiceAsync;
import qbix.sm.client.services.UserServiceAsync;

/**
 *
 * @author iliax
 */
public class MainController implements  ValueChangeHandler<String>, Presenter{
    
    //injected
    private EventBus eventBus;

    //injected
    private UserServiceAsync userService;

    private SessionServiceAsync sessionService=GWT.create(SessionService.class);

    private HasWidgets container;

    //для запоминания предыдущего состояния истории(может понадобится)
    String tempHistoryItem;

    Presenter currentPresenter;

    @Inject  
    public MainController(EventBus eventBus , UserServiceAsync userService) {
        this.eventBus = eventBus;
        this.userService=userService;
        bind();
    }

    private void bind(){
        History.addValueChangeHandler(this);

        //привязываем все ивенты здесь:
        
        eventBus.addHandler(ShowAccoutPageEvent.TYPE, new ShowAccoutPageEvent.Event_Handler() {
            public void onShowAccountPageEvent(ShowAccoutPageEvent showAccoutPageEvent) {
                //
            }
        });
    }

    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) 
          ;//start presenter
        else 
          History.fireCurrentHistoryState();
    }


    public void onValueChange(ValueChangeEvent<String> event) {
       final  String token=event.getValue();

        if(token!=null){
            sessionService.getUserFromSession(new AbstractAsyncCallBack<User>() {
                @Override
                public void handleFailture(Throwable caugh) {
                    //session access failed presenter
                    //currentPresenter.go(container);
                }
                @Override
                public void handleSuccess(User result) {
                    if(result!=null){
                        //owner switch
                    }
                    else{
                        //guest switch
                    }
                        
                    if (currentPresenter!=null)
                        currentPresenter.go(container);
                }
            });
        }
    }
}
