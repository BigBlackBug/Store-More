/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qbix.sm.client;

import gwtupload.client.IFileInput.FileInputType;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.Utils;
import gwtupload.client.MultiUploader;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;


/**
 *
 * @author iliax
 */
public class UploaderPanel extends LayoutContainer {

	public static final float SIZE = 130;

	@Override
	protected void onRender(Element parent, int index) {
            super.onRender(parent, index);

            setLayout(new FitLayout());

            final VerticalPanel uploaderPanel = new VerticalPanel();

            uploaderPanel.setScrollMode(Scroll.AUTO);

            uploaderPanel.setSpacing(10);

            uploaderPanel.setHorizontalAlign(HorizontalAlignment.RIGHT);

            MultiUploader uploader = new MultiUploader(FileInputType.LABEL);

            uploader.setAvoidRepeatFiles(true);

            uploader.setServletPath("uploader.fileUpload");
            uploader.addOnStartUploadHandler(new IUploader.OnStartUploaderHandler() {

                public void onStart(IUploader uploader) {
                    Info.display("Upload started", "Plase don't change your url, while uploading is running...");
                }
             });
            uploader.addOnFinishUploadHandler(new OnFinishUploaderHandler() {
                    public void onFinish(IUploader uploader) {

                        if (uploader.getStatus() == Status.SUCCESS) {

                                String response = uploader.getServerResponse();

                                if (response != null) {
                                        Document doc = XMLParser.parse(response);
                                        String message = Utils.getXmlNodeValue(doc, "message");
                                        String finished = Utils
                                        .getXmlNodeValue(doc, "finished");

                                        Window.alert("Server response: \n" + message + "\n"
                                                        + "finished: " + finished);
                                } else {
                                        Window.alert("Unaccessible server response");
                                }

                                 //uploader.reset();
                        } else 
                            Window.alert("Uploader Status: \n" + uploader.getStatus());
                    }
            });

            uploaderPanel.add(uploader);

//            Button closeButton = new Button("Close",
//                            new SelectionListener<ButtonEvent>() {
//                    public void componentSelected(ButtonEvent ce) {
//                           // MainEntryPoint.closeUploader();
//                    }
//
//            });

         //   uploaderPanel.add(closeButton);

            add(uploaderPanel);
    }

}
