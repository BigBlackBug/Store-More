package qbix.sm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
>>>>>>>>>>>>>>>>>>>> File 1
>>>>>>>>>>>>>>>>>>>> File 2
import com.google.gwt.user.client.rpc.AsyncCallback;
>>>>>>>>>>>>>>>>>>>> File 3
<<<<<<<<<<<<<<<<<<<<
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
>>>>>>>>>>>>>>>>>>>> File 1
>>>>>>>>>>>>>>>>>>>> File 2
import org.apache.http.auth.AUTH;
>>>>>>>>>>>>>>>>>>>> File 3
<<<<<<<<<<<<<<<<<<<<
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
>>>>>>>>>>>>>>>>>>>> File 1
>>>>>>>>>>>>>>>>>>>> File 2
    //yes, it is injected
>>>>>>>>>>>>>>>>>>>> File 3
<<<<<<<<<<<<<<<<<<<<
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
>>>>>>>>>>>>>>>>>>>> File 1
>>>>>>>>>>>>>>>>>>>> File 2
            return userInSession;
        }
        public static void addUserToSession(User user){
            sessionService.addUserToSession(user, new AsyncCallback<Void>() {
                public void onFailure(Throwable caught) {}
                public void onSuccess(Void result) {}
            });
>>>>>>>>>>>>>>>>>>>> File 3
<<<<<<<<<<<<<<<<<<<<
        }
    }
}
