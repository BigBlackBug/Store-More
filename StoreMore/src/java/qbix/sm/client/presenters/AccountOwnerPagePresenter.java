package qbix.sm.client.presenters;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.ModelIconProvider;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Popup;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import qbix.sm.client.Icons;
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

        void setTreeData(SmCategory root);

        void setTableData(LinkedList<SmFile> files);

        Widget asWidget();
    }
   
    private Popup popup = null;
    private Map<Long, String> passwords = new HashMap<Long, String>();
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

    private void bind()
    {

        display.getTreePanel().addListener(Events.OnClick, new Listener<TreePanelEvent<ModelData>>()
        {
            public void handleEvent(final TreePanelEvent<ModelData> be)
            {
                if (popup != null)
                {
                    popup.hide();
                    popup = null;
                }


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
                     
                        if (category.hasPassword())
                            fcService.getAllFilesFromCategory(catId, new AbstractAsyncCallBack<LinkedList<SmFile>>()
                            {
                                @Override
                                public void handleFailure(Throwable caugh)
                                {
                                    throw new UnsupportedOperationException("Not supported yet.");
                                }

                                @Override
                                public void handleSuccess(LinkedList<SmFile> files)
                                {
                                    if (passwords.containsKey(catId))
                                    {
                                        display.setTableData(files);
                                        return;
                                    }
                                    display.setTableData(null);
                                    initPopup(category, files);
                                    popup.showAt(be.getClientX(), be.getClientY());

                                }
                            });
                        else
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

        fcService.getAllCategories(new AsyncCallback<LinkedList<SmCategory>>()
        {
            @Override
            public void onFailure(Throwable caught)
            {
            }

            @Override
            public void onSuccess(LinkedList<SmCategory> result)
            {
                SmCategory root = new SmCategory();
                if (root.getChildCount() != 0)
                    root.removeAll();
                for (SmCategory cat : result)
                    if (cat.getParent() == null)
                        root.add(cat);

                display.getTreePanel().setIconProvider(new ModelIconProvider<ModelData>() {
			public AbstractImagePrototype getIcon(ModelData model) {
				if (((SmCategory) model).hasPassword()) {
					return Icons.ICONS.locked();
				}
				return null;
			}
		});
                display.setTreeData(root);
            }
        });
    }

    private void initPopup(final SmCategory category, final LinkedList<SmFile> files)
    {

        popup = new Popup();

        popup.setSize(300, 55);
        popup.setAnimate(true);
        //  popup.setShadow(true);
        //popup.setBorders(true);
       // popup.setAutoHide(false);
        popup.setConstrainViewport(true);

        final TextField<String> txtField = new TextField<String>();
        Text textInf = new Text("pass");

        Button btn = new Button("setPass", new SelectionListener<ButtonEvent>()
        {
            @Override
            public void componentSelected(ButtonEvent ce)
            {
                if (category.getPassword().equals(txtField.getValue()))
                {
                    display.setTableData(files);
                    passwords.put(category.getCategoryId(), category.getPassword());
                    popup.hide();
                    popup=null;
                }
            }
        });



        final BorderLayout lay = new BorderLayout();
        popup.setLayout(lay);
        final BorderLayoutData nData = new BorderLayoutData(LayoutRegion.NORTH, 20);
        nData.setMargins(new Margins(2));
        popup.add(textInf, nData);
        final BorderLayoutData cData = new BorderLayoutData(LayoutRegion.CENTER);
        nData.setMargins(new Margins(2));
        popup.add(txtField, cData);
        final BorderLayoutData eData = new BorderLayoutData(LayoutRegion.EAST, 50);
        nData.setMargins(new Margins(2));
        popup.add(btn, eData);


    }
}
