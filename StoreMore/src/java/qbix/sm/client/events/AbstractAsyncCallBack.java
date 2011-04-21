package qbix.sm.client.events;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author iliax
 */

public abstract class  AbstractAsyncCallBack<T> implements  AsyncCallback<T> {
    
    protected String FAILTURE_MESSAGE="RPC Callback Failture LOG";

    protected String SUCCESS_MESSAGE="RPC Callback SUCCESS LOG";

    //static final Logger log=Logger.getLogger("AbstractAsyncCallBackLogger");

    public AbstractAsyncCallBack() {
        setLogMessages();
    }

    public AbstractAsyncCallBack(String successLogMessage, String failtureLogMessage) {
        SUCCESS_MESSAGE=successLogMessage;
        FAILTURE_MESSAGE=failtureLogMessage;
    }

    //гостовые методы
    public void onFailure(Throwable caught) {
        logFailture(caught);
        handleFailture(caught);
    }

    public void onSuccess(T result) {
        handleSuccess(result);
        logSuccess();
    }
    //

    //логирование
    protected void logFailture(Throwable caught){
        GWT.log(FAILTURE_MESSAGE, caught);
        //log.severe(FAILTURE_MESSAGE);
    }

    protected void logSuccess(){
        GWT.log(SUCCESS_MESSAGE);
        //log.severe(SUCCESS_MESSAGE);
    }
    //

    public void setOnFailtureMessage(String message){
        FAILTURE_MESSAGE=message;
    }

    public void setOnSuccessMessage(String message){
        SUCCESS_MESSAGE=message;
    }

    //переопределить его при необходимости и установить сообщения логирования внутри
    protected void setLogMessages() {
    }

    //переопределяемые методы
    public abstract void handleFailture(Throwable caugh);

    public abstract void handleSuccess(T result);

}
