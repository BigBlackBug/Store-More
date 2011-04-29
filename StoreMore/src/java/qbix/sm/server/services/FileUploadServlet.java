/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qbix.sm.server.services;
import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import java.io.File;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import qbix.sm.client.services.FCService;

/**
 *
 * @author iliax
 */

public class FileUploadServlet extends UploadAction {

	private static final long serialVersionUID = 1L;

        FCService fCService=null;

    @Override
    public void init() throws ServletException {
        super.init();

        ApplicationContext context = WebApplicationContextUtils.
            getRequiredWebApplicationContext(getServletContext());
        fCService=(FCService) context.getBean("FCService");
    }


    @Override
	public String executeAction(HttpServletRequest request,
			List<FileItem> sessionFiles) throws UploadActionException {
		String response = "Received file:";

                File file=new File(".");
                
		for (FileItem item : sessionFiles) {
			if (!item.isFormField()) {
				try {
//                                    file = File.createTempFile("receivedFile", ".tmp",
//                                        new File(file.getCanonicalPath()));
//					 item.write(file);
                                    file=new File(file.getCanonicalPath()+"/for_saved_files/"+item.getName());
                                  //  File tempFile=File.createTempFile("receivedFile_", "", file);

                                    item.write(file);
					 response += " " + file.getPath();

					response += " " + item.getName() + ", \nsize=  "
					+ item.getSize() + ", \nContentType= "
					+ item.getContentType();

				} catch (Exception e) {
					throw new UploadActionException(e.getMessage());
				}
			}
                        getSessionFileItems(request);
		}

		try {
			// Remove files from session
			removeSessionFileItems(request);
		} catch (Exception e) {
			throw new UploadActionException(e.getMessage());
		}

		// Send information of the received files to the client.
		return response;
	}

}

