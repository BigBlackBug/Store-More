/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.SmCategory;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.beans.User;

/**
 *
 * @author BigBlackBug
 */
public class FileDAOImpl implements FileDao
{
    LinkedList<SmFile> files;

    public FileDAOImpl()
    {
        files = new LinkedList<SmFile>();
    }

    public LinkedList<SmFile> getAll()
    {
        return files;
    }

    public LinkedList<SmFile> getAllFilesOfUser(String userName)
    {
        // UserDao userDao=new UserDAOImpl();
        //User user=userDao.getByName(userName);
        CategoryDao cat = new CategoryDAOImpl();
        LinkedList<SmCategory> cats = cat.getAllCategoriesOfUser(userName);
        if(!cats.isEmpty())
            System.out.println("cats not empty");
        LinkedList<SmFile> files1 = new LinkedList<SmFile>();

        for (SmCategory c : cats)
            files1.addAll(c.getFiles());
        if(!files1.isEmpty())
            System.out.println("files1 not empty");
        return files1;
    }

    public LinkedList<SmFile> getAllFilesOfUserById(Long id)
    {
        CategoryDao cat = new CategoryDAOImpl();
        LinkedList<SmCategory> cats = cat.getAllCategoriesOfUserById(id);

        LinkedList<SmFile> files1 = new LinkedList<SmFile>();

        for (SmCategory c : cats)
            files1.addAll(c.getFiles());
        return files1;
    }

    public LinkedList<SmFile> getAllFilesFromCategory(Long categoryId)
    {
        CategoryDao cat = new CategoryDAOImpl();
        return cat.getById(categoryId).getFiles();// LinkedList<SmCategory> cats=cat.getAllCategoriesOfUser(userName);


    }

    public void add(SmFile newFile)
    {
        files.add(newFile);
    }

    public void deleteById(Long fileId)
    {

        for(SmFile f:files)
            if(f.getFileId()==fileId)
                files.remove(f);
    }
}
