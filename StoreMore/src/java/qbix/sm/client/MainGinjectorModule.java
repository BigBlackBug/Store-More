package qbix.sm.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 *
 * @author iliax
 */

public class MainGinjectorModule extends AbstractGinModule{

    @Override
    protected void configure() {
        //base
            bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class); //синглтон!
        //

        bindViewsToPresenters();
        bindServicesToPresenters();

        bindSmthElse();
    }

    private void bindViewsToPresenters(){
        
    }

    private void bindServicesToPresenters(){
        
    }

    private void bindSmthElse(){
        
    }
}
