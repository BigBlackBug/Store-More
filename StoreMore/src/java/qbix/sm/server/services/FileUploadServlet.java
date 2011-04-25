/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package qbix.sm.server.services;
import gwtupload.server.UploadAction;
import gwtupload.server.exceptions.UploadActionException;
import java.io.File;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author iliax
 */

public class FileUploadServlet extends UploadAction {

	private static final long serialVersionUID = 1L;

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

