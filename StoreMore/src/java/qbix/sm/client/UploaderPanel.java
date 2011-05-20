/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qbix.sm.client;
import com.google.gwt.event.dom.client.ClickEvent;
import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.Utils;
import gwtupload.client.MultiUploader;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Popup;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import gwtupload.client.IUploadStatus;
import gwtupload.client.SingleUploader;


/**
 *
 * @author iliax
 */
public class UploaderPanel extends LayoutContainer {

        private boolean isActive=false;
        
        private SingleUploader singleUploader;
        
        Button uploadPopupButton=new Button("Upload!");

        Popup uploadPopup=new Popup();

        MessageBox prompt=null;
        
	@Override
	protected void onRender(Element parent, int index) {
            super.onRender(parent, index);

            setLayout(new FitLayout());

            final VerticalPanel uploaderPanel = new VerticalPanel();

            uploaderPanel.setScrollMode(Scroll.AUTO);

            uploaderPanel.setSpacing(10);

            uploaderPanel.setHorizontalAlign(HorizontalAlignment.RIGHT);

            singleUploader=new SingleUploader();
            singleUploader.setServletPath("uploader.fileUpload");

            singleUploader.addOnStatusChangedHandler(new IUploader.OnStatusChangedHandler() {
                public void onStatusChanged(IUploader uploader) {
                    if(uploader.getStatus().equals(IUploadStatus.Status.CHANGED)){
                        prompt = MessageBox.prompt("qweqwe", "enter id:");
                        prompt.addCallback(new Listener<MessageBoxEvent>() {
                            public void handleEvent(MessageBoxEvent be) {
                                singleUploader.getFileInput().setName(be.getValue()+"__");
                                Info.display("Upload","press SEND button to start upload");
                                prompt.close();
                                singleUploader.submit();
                            }
                        });
                    }                 
                }
            });

            singleUploader.addOnStartUploadHandler(new IUploader.OnStartUploaderHandler() {
                public void onStart(IUploader uploader) {
                    isActive=true;
                    //uploadPopup.hide();
                    //uploadPopup.setAutoHide(true);
                    Info.display("Upload","Uploading file...");
                }
            });

            singleUploader.addOnFinishUploadHandler(new OnFinishUploaderHandler(){
                public void onFinish(IUploader uploader){

                    isActive=false;
                    Info.display("Upload","upload finished!");

                    if (uploader.getStatus() == Status.SUCCESS){
                        String response = uploader.getServerResponse();
                        if (response != null){
                            Document doc = XMLParser.parse(response);
                            String message = Utils.getXmlNodeValue(doc, "message");
                            String finished = Utils.getXmlNodeValue(doc, "finished");

                            Window.alert("Server response: \n" + message + "\n"
                                    + "finished: " + finished);
                        } else
                            Window.alert("Unaccessible server response");
                    } else
                        Window.alert("Uploader Status: \n" + uploader.getStatus());
                }
            });


            uploaderPanel.add(singleUploader);
            uploadPopup.add(uploaderPanel);

            uploadPopup.setAnimate(true);
            uploadPopupButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
                @Override
                public void componentSelected(ButtonEvent ce) {
                    if(!uploadPopup.isVisible()){
                        uploadPopup.show(uploadPopupButton);
                        uploadPopupButton.setText("Click to close uploder");
                    } else {
                        uploadPopupButton.setText("Upload!");
                        uploadPopup.hide();
                    }
                }
            });

            uploadPopup.setAutoHide(false);
            add(uploadPopupButton);
    }

    public boolean isActive(){
        return isActive;
    }
}