package qbix.sm.client.presenters;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import java.util.LinkedList;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.beans.User;
import qbix.sm.client.events.AbstractAsyncCallBack;
import qbix.sm.client.services.FCService;
import qbix.sm.client.services.FCServiceAsync;

/**
 *
 * @author iliax
 */
public class AccountOwnerPagePresenter implements Presenter
{
    User pageOwner;
    FCServiceAsync fcService;
    @Inject
    public AccountOwnerPagePresenter(FCServiceAsync fcService)
    {
        this.fcService = fcService;
    }

    public void go(final HasWidgets container)
    {
        container.clear();
        container.add(new Label(pageOwner.getName() + "'s acc OWNER presenter"));
       
        fcService.getAllFilesOfUser(pageOwner.getName(), new AbstractAsyncCallBack<LinkedList<SmFile>>()
        {
            @Override
            public void handleFailture(Throwable caugh)
            {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void handleSuccess(LinkedList<SmFile> result)
            {
                Anchor questionNumberAnchor = new Anchor();
                // Window.alert(Window.Location.getHref());
                questionNumberAnchor.setHref("/StoreMore/download?fileName="+result.peek().getPathName());
                questionNumberAnchor.setText("WHHHAT");
                questionNumberAnchor.addClickHandler(new ClickHandler()
                {
                    @Override
                    public void onClick(ClickEvent event)
                    {
                        System.out.println("Jumping to panel " + 5);

                    }
                });
                container.add(questionNumberAnchor);
            }
        });

    }

    public void setOwner(User userInSession)
    {
        pageOwner = userInSession;
    }
}
