package qbix.sm.client.presenters;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import qbix.sm.client.beans.SmCategory;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.beans.User;
import qbix.sm.client.events.AbstractAsyncCallback;
import qbix.sm.client.services.FCServiceAsync;

/**
 *
 * @author iliax
 */
public class AccountOwnerPagePresenter implements Presenter
{
    // RpcProxy<LinkedList<SmFile>> fileProxy;
    TreeStore<ModelData> treeStore = new TreeStore<ModelData>();
    SmCategory root = new SmCategory();
    ListStore<BeanModel> fileStore = new ListStore<BeanModel>();
    BeanModelReader reader = new BeanModelReader();
    Grid<BeanModel> grid;
    TreePanel<ModelData> treePanel;
    ColumnModel cm;
    //ListField<BeanModel> catList;
    User pageOwner;
    FCServiceAsync fcService;
    // ListLoader<ListLoadResult<BeanModel>> loader;

    @Inject
    public AccountOwnerPagePresenter(FCServiceAsync fcService)
    {
        this.fcService = fcService;


    }

    public void go(final HasWidgets container)
    {

        container.clear();
        container.add(new Label(pageOwner.getName() + "'s acc OWNER presenter"));

        //Каты
        /////////////////////////////////////////////////////


        fcService.getAllCategories(new AsyncCallback<LinkedList<SmCategory>>()
        {
            @Override
            public void onFailure(Throwable caught)
            {
                Window.alert("error");
            }

            @Override
            public void onSuccess(LinkedList<SmCategory> result)
            {
                if (root.getChildCount() != 0)
                    root.removeAll();
                for (SmCategory cat : result)
                    if (cat.getParent() == null)
                        root.add(cat);
                if (treeStore.getChildCount() != 0)
                    treeStore.removeAll();
                treeStore.add(root.getChildren(), true);
            }
        });

        treeStore.add(root.getChildren(), true);

        treePanel = new TreePanel<ModelData>(treeStore);
       // treePanel.expandAll();
        treePanel.setDisplayProperty("name");
        treePanel.setWidth(150);
        treePanel.setAutoExpand(true);


        treePanel.getStyle().setLeafIcon(treePanel.getStyle().getNodeCloseIcon());

        treePanel.addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>()
        {
            public void handleEvent(TreePanelEvent<ModelData> be)
            {
                final BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(SmFile.class);
                final Long catId = ((Long) be.getItem().get("categoryId"));

                fcService.getAllFilesFromCategory(catId, new AbstractAsyncCallback<LinkedList<SmFile>>()
                {
                    @Override
                    public void handleFailure(Throwable caugh)
                    {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }

                    @Override
                    public void handleSuccess(LinkedList<SmFile> result)
                    {
                        fileStore.removeAll();
                        //fileStore.
                        fileStore.add(beanModelFactory.createModel(result));
                    }
                });
            }

            ;
        });
        container.add(treePanel);


        //Табла
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId("realName");
        column.setHeader("Name");
        column.setWidth(200);
        column.setRenderer(new GridCellRenderer()
        {
            public Object render(ModelData model, String property, ColumnData config,
                    int rowIndex, int colIndex, ListStore store, Grid grid)
            {
                String realName = (String) model.get(property);
                String pathName = (String) model.get("pathName");
                Anchor questionNumberAnchor = new Anchor();
                questionNumberAnchor.setHref("/StoreMore/download?fileName=" + pathName);
                questionNumberAnchor.setText(realName);
                return questionNumberAnchor;

            }
        });
        configs.add(column);

        column = new ColumnConfig("size", "size", 100);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig("uploadDate", "uploading date", 100);

        column.setDateTimeFormat(DateTimeFormat.getFormat("dd MMMM yyyy H:mm"));
        column.setAlignment(HorizontalAlignment.LEFT);

        column.setWidth(300);
        configs.add(column);


        cm = new ColumnModel(configs);
        grid = new Grid<BeanModel>(fileStore, cm);
        grid.setStyleAttribute("borderTop", "none");
        //grid.setAutoExpandColumn("name");
        grid.setBorders(true);
        grid.setStripeRows(true);

        ContentPanel cp = new ContentPanel();
        cp.setBodyBorder(false);
        cp.setHeading("Employee List");
        cp.setButtonAlign(HorizontalAlignment.CENTER);
        cp.setLayout(new FitLayout());
        cp.setSize(700, 300);
        cp.add(grid);
        container.add(cp);


    }

    public void setOwner(User userInSession)
    {
        pageOwner = userInSession;
    }
}
