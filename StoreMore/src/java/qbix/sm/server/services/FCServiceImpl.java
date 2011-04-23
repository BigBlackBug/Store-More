/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.LinkedList;

import qbix.sm.client.services.FCService;
import qbix.sm.client.beans.SmCategory;
import qbix.sm.client.beans.SmFile;
import qbix.sm.server.dao.CategoryDao;
import qbix.sm.server.dao.FileDao;

/**
 *
 * @author BigBlackBug
 */
public class FCServiceImpl implements FCService  // extends RemoteServiceServlet
{
    private FileDao fileDAO;
    private CategoryDao categoryDAO;

    public FCServiceImpl()
    {
    }

    public CategoryDao getCategoryDAO()
    {
        return categoryDAO;
    }

    public void setCategoryDAO(CategoryDao categoryDAO)
    {
        this.categoryDAO = categoryDAO;
    }

    public FileDao getFileDAO()
    {
        return fileDAO;
    }

    public void setFileDAO(FileDao fileDAO)
    {
        this.fileDAO = fileDAO;
    }

    public LinkedList<SmFile> getAllFiles()
    {
        return fileDAO.getAll();
    }

    public LinkedList<SmFile> getAllFilesOfUser(String userName)
    {
        return fileDAO.getAllFilesOfUser(userName);
    }

    public LinkedList<SmFile> getAllFilesOfUserById(Long id)
    {
        return fileDAO.getAllFilesOfUserById(id);
    }

    public LinkedList<SmFile> getAllFilesFromCategory(Long categoryId)
    {

        return fileDAO.getAllFilesFromCategory(categoryId);
    }

    public void addNewFile(SmFile newFile)
    {
        fileDAO.add(newFile);
    }

    public void deleteFileById(Long fileId)
    {
        fileDAO.deleteById(fileId);
    }

    public SmCategory getCategoryById(Long categoryId)
    {
        return categoryDAO.getById(categoryId);
    }

    public LinkedList<SmCategory> getAllCategoriesOfUserById(Long userId)
    {
        return categoryDAO.getAllCategoriesOfUserById(userId);
    }

    public LinkedList<SmCategory> getAllCategoriesOfUser(String userName)
    {
        return categoryDAO.getAllCategoriesOfUser(userName);
    }

    public void addNewCategory(SmCategory newCategory)
    {
        categoryDAO.add(newCategory);
    }

    public void deleteCategoryById(Long categoryId)
    {
        categoryDAO.deleteById(categoryId);
    }

    public void deleteAllCategoriesOfUserById(Long userId)
    {
        categoryDAO.deleteAllCategoriesOfUserById(userId);
    }

    public void deleteAllCategoriesOfUser(String userName)
    {
        categoryDAO.deleteAllCategoriesOfUser(userName);
    }

    public LinkedList<SmCategory> getAllCategories()
    {
        return categoryDAO.getAll();
    }
}
