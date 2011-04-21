package qbix.sm.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;


/**
 *
 * @author iliax
 */

@GinModules(MainGinjectorModule.class)
public interface MainGinjector extends Ginjector{

    MainController getMainController();
    
    //presenters:

}
