/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.client.views;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
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
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import qbix.sm.client.beans.SmCategory;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.icons.Icons;
import qbix.sm.client.presenters.AccountOwnerPagePresenter;

/**
 *
 * @author BigBlackBug
 */
public class AccountOwnerPageView extends Composite implements AccountOwnerPagePresenter.Display
{
    TreeStore<ModelData> treeStore = new TreeStore<ModelData>();
    ListStore<BeanModel> fileStore = new ListStore<BeanModel>();
    Grid<BeanModel> grid;
    //Button deleteButton;
    TreePanel<ModelData> treePanel;
    ColumnModel columnModel;
    Menu treeContextMenu;
    MenuItem addCategoryMenu;
    MenuItem removeCategoryMenu;
    Menu gridMenu;
    MenuItem removeFileMenu;
    //public static final Icons ICONS = GWT.create(Icons.class);

    public AccountOwnerPageView()
    {

        HorizontalPanel contents = new HorizontalPanel();//new RowLayout(Orientation.HORIZONTAL));
        // contents.setLayout(new HBoxLayout());
        initWidget(contents);
        treePanel = new TreePanel<ModelData>(treeStore);
        // treePanel.expandAll();
        treePanel.setDisplayProperty("name");
        treePanel.setWidth(150);
        //treePanel.setAutoHeight(true);


        treePanel.setAutoExpand(true);
        treePanel.setAutoWidth(true);



        treePanel.getStyle().setLeafIcon(treePanel.getStyle().getNodeCloseIcon());

        treeContextMenu = new Menu();
        treeContextMenu.setWidth(140);

        addCategoryMenu = new MenuItem();
        addCategoryMenu.setText("Add cat");
        addCategoryMenu.setIcon(Icons.ICONS.add());
        treeContextMenu.add(addCategoryMenu);

        removeCategoryMenu = new MenuItem();
        removeCategoryMenu.setText("Remove cat");
        removeCategoryMenu.setIcon(Icons.ICONS.delete());
        treeContextMenu.add(removeCategoryMenu);

        treePanel.setContextMenu(treeContextMenu);
        contents.add(treePanel);


//        gridMenu=new Menu();
//        gridMenu.setWidth(100);
//        removeFileMenu.setText("remove this file");
//        removeFileMenu.setIcon(Icons.ICONS.cross());
//        gridMenu.add(removeFileMenu);
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


        column = new ColumnConfig("uploadDate", "uploading date", 150);
        column.setDateTimeFormat(DateTimeFormat.getFormat("dd MMMM yyyy H:mm"));
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);


//        column = new ColumnConfig();
//        column.setHeader("delete?");
//        column.setWidth(50);
//        column.setRenderer(new GridCellRenderer()
//        {
//            public Object render(final ModelData model, String property, ColumnData config,
//                    final int rowIndex, int colIndex, ListStore store, Grid grid)
//            {
//
//                Button button = new Button();
//                button.setIcon(Icons.ICONS.cross());
//
//                button.addSelectionListener(new SelectionListener<ButtonEvent>()
//                {
//                    @Override
//                    public void componentSelected(ButtonEvent ce)
//                    {
//                        fileStore.remove(rowIndex);
//                    }
//                });
//                return button;
//
//            }
//        });
//        configs.add(column);


        columnModel = new ColumnModel(configs);
        grid = new Grid<BeanModel>(fileStore, columnModel);
        grid.setStyleAttribute("borderTop", "none");
        //grid.setAutoExpandColumn("name");
        grid.setBorders(true);
        grid.setStripeRows(true);
        //grid.setContextMenu(gridMenu);

        ContentPanel gridContentPanel = new ContentPanel();
        gridContentPanel.setBodyBorder(false);
        gridContentPanel.setHeading("FILES");
        gridContentPanel.setScrollMode(Scroll.NONE);
        gridContentPanel.setButtonAlign(HorizontalAlignment.CENTER);
        gridContentPanel.setLayout(new FitLayout());
        gridContentPanel.setSize(500, 300);
        gridContentPanel.add(grid);
        contents.add(gridContentPanel);

    }

    public TreePanel<ModelData> getTreePanel()
    {
        return treePanel;
    }

    public void setTreeData(LinkedList<SmCategory> cats)
    {

        SmCategory root = new SmCategory();
        if (root.getChildCount() != 0)
            root.removeAll();
        for (SmCategory cat : cats)
            if (cat.getParent() == null)
                root.add(cat);


        if (treeStore.getChildCount() != 0)
            treeStore.removeAll();
        treeStore.add(root.getChildren(), true);
        treePanel.expandAll();


    }

    public void setTableData(LinkedList<SmFile> files)
    {
        fileStore.removeAll();
        if (files == null)
            return;
        final BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(SmFile.class);

        fileStore.add(beanModelFactory.createModel(files));

    }

    @Override
    public Widget asWidget()
    {
        return this;
    }

    public MenuItem getAddCategoryMenu()
    {
        return addCategoryMenu;
    }

    public MenuItem getDeleteCategoryMenu()
    {
        return removeCategoryMenu;
    }

    public TreeStore<ModelData> getTreeStore()
    {
        return treeStore;
    }

    public ListStore<BeanModel> getFileStore()
    {
        return fileStore;
    }

    public Grid<BeanModel> getGrid()
    {
        return grid;
    }

    public MenuItem getRemoveFileMenu()
    {
        return removeFileMenu;
    }
}
