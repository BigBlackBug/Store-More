package qbix.sm.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */
public class ShowAccoutPageEvent extends  GwtEvent<ShowAccoutPageEvent.Event_Handler>{

    public static Type<ShowAccoutPageEvent.Event_Handler> TYPE =
            new Type<ShowAccoutPageEvent.Event_Handler>();

    private User accountOwner;

    public User getAccountOwner(){ return accountOwner; }

    public ShowAccoutPageEvent(User accountOwner) {
        this.accountOwner = accountOwner;
    }

    @Override
    public Type<ShowAccoutPageEvent.Event_Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(Event_Handler handler) {
        handler.onShowAccountPageEvent(this);
    }

///////////////////////////////////////////////////////////////////
    public interface Event_Handler extends EventHandler{
        public void onShowAccountPageEvent(ShowAccoutPageEvent showAccoutPageEvent);
    }
}
