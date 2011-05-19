/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.client.events;

/**
 *
 * @author BigBlackBug
 */
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AbstractAsyncCallBack<T> implements AsyncCallback<T>
{
    protected String FAILURE_MESSAGE = "RPC Callback Failure LOG";
    protected String SUCCESS_MESSAGE = "RPC Callback SUCCESS LOG";

    //static final Logger log=Logger.getLogger("AbstractAsyncCallBackLogger");
    public AbstractAsyncCallBack()
    {
        setLogMessages();
    }

    public AbstractAsyncCallBack(String successLogMessage, String failureLogMessage)
    {
        SUCCESS_MESSAGE = successLogMessage;

        FAILURE_MESSAGE = failureLogMessage;

    }

    //гостовые методы
    public void onFailure(Throwable caught)
    {
        logFailure(caught);
        handleFailure(caught);
    }

    public void onSuccess(T result)
    {
        handleSuccess(result);
        logSuccess();
    }
    //

    //логирование
    protected void logFailure(Throwable caught)
    {
        GWT.log(FAILURE_MESSAGE, caught);
        //log.severe(FAILURE_MESSAGE);

    }

    protected void logSuccess()
    {
        GWT.log(SUCCESS_MESSAGE);
        //log.severe(SUCCESS_MESSAGE);
    }
    //

    public void setOnFailureMessage(String message)
    {

        FAILURE_MESSAGE = message;
    }

    public void setOnSuccessMessage(String message)
    {
        SUCCESS_MESSAGE = message;
    }

    //переопределить его при необходимости и установить сообщения логирования внутри
    protected void setLogMessages()
    {
    }

    //переопределяемые методы
    public abstract void handleFailure(Throwable caught);

    public abstract void handleSuccess(T result);
}
