package qbix.sm.client.presenters;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;

/**
 *
 * @author iliax
 */
public class MainPagePresenter implements Presenter
{
    EventBus eventBus;

    @Inject
    public MainPagePresenter(EventBus eventBus)
    {
        this.eventBus = eventBus;
    }

    public void go(HasWidgets container)
    {
        container.clear();
        container.add(new Label("MainPresenter"));
    }
}
