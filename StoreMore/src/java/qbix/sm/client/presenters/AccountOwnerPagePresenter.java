package qbix.sm.client.presenters;

import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.ModelData;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Random;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import java.util.LinkedList;
import qbix.sm.client.beans.SmCategory;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.beans.User;
import qbix.sm.client.events.AbstractAsyncCallBack;
import qbix.sm.client.services.FCServiceAsync;

/**
 *
 * @author iliax
 */
public class AccountOwnerPagePresenter implements Presenter
{
    public interface Display
    {
        TreePanel<ModelData> getTreePanel();

        Grid<BeanModel> getGrid();

        MenuItem getRemoveFileMenu();

        void setTreeData(LinkedList<SmCategory> cats);

        TreeStore<ModelData> getTreeStore();

        ListStore<BeanModel> getFileStore();

        void setTableData(LinkedList<SmFile> files);

        MenuItem getAddCategoryMenu();

        MenuItem getDeleteCategoryMenu();

        Widget asWidget();
    }
    private User pageOwner;
    //injected
    private FCServiceAsync fcService;
    //injected
    private EventBus eventBus;
    //injected
    private Display display;

    @Inject
    public AccountOwnerPagePresenter(FCServiceAsync fcService, EventBus eventBus, Display display)
    {
        this.fcService = fcService;
        this.display = display;
        this.eventBus = eventBus;


    }
    //TODO Saving new store to the db

    private void bind()
    {
        display.getAddCategoryMenu().addSelectionListener(new SelectionListener<MenuEvent>()
        {
            public void componentSelected(MenuEvent ce)
            {
                TreePanel tree = display.getTreePanel();
                final SmCategory selectedCat = (SmCategory) tree.getSelectionModel().getSelectedItem();

                Integer r = Random.nextInt(1500);
                final SmCategory newCat = new SmCategory(Long.valueOf(r), "name0", null, "qqq");


                display.getTreeStore().add(selectedCat, newCat, true);
                display.getTreePanel().expandAll();
                //reloading db here

            }
        });

        display.getDeleteCategoryMenu().addSelectionListener(new SelectionListener<MenuEvent>()
        {
            @Override
            public void componentSelected(MenuEvent ce)
            {
                //display.getTreePanel().setAutoWidth(false);
                final SmCategory selectedCat = (SmCategory) display.getTreePanel().getSelectionModel().getSelectedItem();
                display.getTreeStore().remove(selectedCat);
                //reloading db here
            }
        });
        display.getTreePanel().addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>()
        {
            public void handleEvent(final TreePanelEvent<ModelData> be)
            {

                final Long catId = ((Long) be.getItem().get("categoryId"));

                fcService.getCategoryById(catId, new AbstractAsyncCallBack<SmCategory>()
                {
                    @Override
                    public void handleFailure(Throwable caught)
                    {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void handleSuccess(final SmCategory category)
                    {

                        fcService.getAllFilesFromCategory(catId, new AbstractAsyncCallBack<LinkedList<SmFile>>()
                        {
                            @Override
                            public void handleFailure(Throwable caugh)
                            {
                                throw new UnsupportedOperationException("Not supported yet.");
                            }

                            @Override
                            public void handleSuccess(LinkedList<SmFile> result)
                            {
                                display.setTableData(result);

                            }
                        });


                    }
                });


            }
        });
    }

    public void go(final HasWidgets container)
    {
        //initPopup();
        bind();

        setDataToTree();
        container.clear();
        container.add(display.asWidget());
        container.add(new Label(pageOwner.getName() + "'s acc OWNER presenter"));



    }

    public void setOwner(User userInSession)
    {
        pageOwner = userInSession;
    }

    private void setDataToTree()
    {

        fcService.getAllCategoriesOfUserById(pageOwner.getUserId(), new AsyncCallback<LinkedList<SmCategory>>()
        {
            @Override
            public void onFailure(Throwable caught)
            {
            }

            @Override
            public void onSuccess(LinkedList<SmCategory> result)
            {
                display.setTreeData(result);
            }
        });
    }
}
