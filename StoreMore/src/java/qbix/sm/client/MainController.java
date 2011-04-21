package qbix.sm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;
import org.apache.http.auth.AUTH;
import qbix.sm.client.beans.User;
import qbix.sm.client.events.AbstractAsyncCallBack;
import qbix.sm.client.presenters.Presenter;
import qbix.sm.client.services.SessionService;
import qbix.sm.client.services.SessionServiceAsync;

/**
 *
 * @author iliax
 */
public class MainController implements  ValueChangeHandler<String>, Presenter{
    
    //injected
    private EventBus eventBus;

    private HasWidgets container;

    @Inject  
    public MainController(EventBus eventBus) {
        this.eventBus = eventBus;
        bind();
    }

    private void bind(){
        History.addValueChangeHandler(this);

        //привязываем все ивенты here
    }

    public void go(final HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) 
          ;//start presenter
        else 
          History.fireCurrentHistoryState();   
    }


    public void onValueChange(ValueChangeEvent<String> event) {
        String token=event.getValue();

        if(token!=null){
            Presenter presenter = null;
            
            if(SessionChecker.isAuthenticated()){
                //switch
            }
            else{
                //switch
            }
            
            
            if (presenter!=null)
                presenter.go(container);
        }
    }

    private static class SessionChecker {
        static private SessionServiceAsync sessionService=GWT.create(SessionService.class);
        static boolean auth=false;
        static User userInSession=null;
        static boolean isAuthenticated(){
            sessionService.isAuthenticated(new AsyncCallback<Boolean>() {
                public void onFailure(Throwable caught) {}
                public void onSuccess(Boolean result) {
                    auth=result;
                }
            });
            return auth;
        }
        static User getUserFromSesion(){
            sessionService.getUserFromSession(new AsyncCallback<User>() {
                public void onFailure(Throwable caught) { }
                public void onSuccess(User result) {
                    userInSession=result;
                }
            });
            return userInSession;
        }
    }
}
