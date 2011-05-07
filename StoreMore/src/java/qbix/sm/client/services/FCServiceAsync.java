/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.LinkedList;
import qbix.sm.client.beans.SmCategory;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.events.AbstractAsyncCallback;

/**
 *
 * @author BigBlackBug
 */
public interface FCServiceAsync
{
    public void getAllFiles(AsyncCallback<LinkedList<SmFile>> asyncCallback);

    public void getAllFilesOfUser(String userName, AsyncCallback<LinkedList<SmFile>> asyncCallback);

    public void getAllFilesOfUserById(Long id, AsyncCallback<LinkedList<SmFile>> asyncCallback);

    public void getAllFilesFromCategory(Long categoryId, AsyncCallback<LinkedList<SmFile>> asyncCallback);

    public void addNewFile(SmFile newFile, AsyncCallback<Void> asyncCallback);

    public void deleteFileById(Long fileId, AsyncCallback<Void> asyncCallback);

    public void getCategoryById(Long categoryId, AsyncCallback<SmCategory> asyncCallback);

    public void getAllCategoriesOfUserById(Long userId, AsyncCallback<LinkedList<SmCategory>> asyncCallback);

    public void getAllCategoriesOfUser(String userName, AsyncCallback<LinkedList<SmCategory>> asyncCallback);

    public void addNewCategory(SmCategory newCategory, AsyncCallback<Void> asyncCallback);

    public void deleteCategoryById(Long categoryId, AsyncCallback<Void> asyncCallback);

    public void deleteAllCategoriesOfUserById(Long userId, AsyncCallback<Void> asyncCallback);

    public void deleteAllCategoriesOfUser(String userName, AsyncCallback<Void> asyncCallback);

    public void getAllCategories(AsyncCallback<LinkedList<SmCategory>> asyncCallback);
}
