/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.LinkedList;
import org.workingonit.gwtbridge.GwtRemoteService;
import qbix.sm.client.beans.SmCategory;
import qbix.sm.client.beans.SmFile;

/**
 *
 * @author BigBlackBug
 */
@RemoteServiceRelativePath("/services/FCService.rpc")
@GwtRemoteService("/services/FCService.rpc")
public interface FCService extends RemoteService
{
    //files
    LinkedList<SmFile> getAllFiles();

    LinkedList<SmFile> getAllFilesOfUser(String userName);

    LinkedList<SmFile> getAllFilesOfUserById(Long id);

    LinkedList<SmFile> getAllFilesFromCategory(Long categoryId);

    void addNewFile(SmFile newFile);

    void deleteFileById(Long fileId);

    //cats
    LinkedList<SmCategory> getAllCategories();

    SmCategory getCategoryById(Long categoryId);

    LinkedList<SmCategory> getAllCategoriesOfUserById(Long userId);

    LinkedList<SmCategory> getAllCategoriesOfUser(String userName);

    //категория уже имеет ссылки на родителя и юзера!(все обертки делаются в сервисе!)
    void addNewCategory(SmCategory newCategory);

    void deleteCategoryById(Long categoryId);

    void deleteAllCategoriesOfUserById(Long userId);

    void deleteAllCategoriesOfUser(String userName);
}
