package qbix.sm.client.presenters;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.beans.User;
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


        RpcProxy<LinkedList<SmFile>> proxy = new RpcProxy<LinkedList<SmFile>>()
        {
            @Override
            protected void load(Object loadConfig, AsyncCallback<LinkedList<SmFile>> callback)
            {
                fcService.getAllFilesOfUser(pageOwner.getName(), callback);
            }
        };
        BeanModelReader reader = new BeanModelReader();
        ListLoader<ListLoadResult<BeanModel>> loader = new BaseListLoader<ListLoadResult<BeanModel>>(proxy, reader);
        ListStore<BeanModel> feedStore = new ListStore<BeanModel>(loader);
        loader.load();
        ListField<BeanModel> list = new ListField<BeanModel>();
        list.setStore(feedStore);
        list.setDisplayField("realName");

        container.add(list);




        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId("realName");
        column.setHeader("Name");
        column.setWidth(200);
        column.setRenderer(new GridCellRenderer() {

            public Object render(ModelData model, String property, ColumnData config,
                    int rowIndex, int colIndex, ListStore store, Grid grid)
            {
                String realName = (String)model.get(property);
                String pathName=(String)model.get("pathName");
                Anchor questionNumberAnchor = new Anchor();
                questionNumberAnchor.setHref("/StoreMore/download?fileName="+pathName);
                questionNumberAnchor.setText(realName);
                return questionNumberAnchor;

            }
        });
        configs.add(column);

        column = new ColumnConfig("size", "size", 150);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

       // column=new ColumnConfig("uploadDate","uploading date", 100);
         //column.setAlignment(HorizontalAlignment.LEFT);
         //configs.add(column);


        ColumnModel cm = new ColumnModel(configs);
        Grid<BeanModel> grid = new Grid<BeanModel>(feedStore, cm);
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

       

        /*Anchor questionNumberAnchor = new Anchor();
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
        container.add(questionNumberAnchor);//////////////



        //ListStore<BeanModel> feedStore=new ListStore<BeanModel>();
        //BeanModelFactory beanModelFactory=BeanModelLookup.get().getFactory(SmFile.class);
        //так мы добавляем в стор.
        //feedStore.add(beanModelFactory.createModel(result));
        //ListField<BeanModel> list=new ListField<BeanModel>();
        //list.setStore(feedStore);
        //list.setDisplayField("realName");

        //container.add(list);

*/

    }

    public void setOwner(User userInSession)
    {
        pageOwner = userInSession;
    }
}
