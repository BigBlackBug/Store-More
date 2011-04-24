package qbix.sm.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import qbix.sm.client.presenters.AccountOwnerPagePresenter;
import qbix.sm.client.presenters.AccountPagePresenter;
import qbix.sm.client.presenters.MainPagePresenter;


/**
 *
 * @author iliax
 */

@GinModules(MainGinjectorModule.class)
public interface MainGinjector extends Ginjector{

    MainController getMainController();

    HeaderPanel getHeaderPanel();
    
    //presenters:

    AccountPagePresenter getAccountPagePresenter();

    AccountOwnerPagePresenter getAccountOwnerPagePresenter();

    MainPagePresenter getMainPagePresenter();

}
