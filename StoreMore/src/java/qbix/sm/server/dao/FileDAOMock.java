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
public class FileDAOMock implements FileDao
{
    private static LinkedList<SmFile> files = new LinkedList<SmFile>();

    public FileDAOMock()
    {
       
    }

    public LinkedList<SmFile> getAll()
    {
        
        return files;
    }

    public LinkedList<SmFile> getAllFilesOfUser(String userName)
    {
        // UserDao userDao=new UserDAOImpl();
        //User user=userDao.getByName(userName);
        LinkedList<SmFile> q=new LinkedList<SmFile>(); q.add(new SmFile(1L,null, "realname","C://123.exe", 123456L, null));
        return q;
        /*LinkedList<SmCategory> cats = new LinkedList<SmCategory>();//SmCategory cat = new SmCategory();
       
        for (SmCategory c : CategoryDAOMock.getcats())
            if (c.getUser().getName().equals(userName))
                cats.add(c);


        LinkedList<SmFile> files1 = new LinkedList<SmFile>();

        for (SmCategory c : cats)
            files1.addAll(c.getFiles());

        return files1;*/
    }

    public LinkedList<SmFile> getAllFilesOfUserById(Long id)
    {

        LinkedList<SmCategory> cats = new LinkedList<SmCategory>();//SmCategory cat = new SmCategory();

        for (SmCategory c : CategoryDAOMock.getcats())
            if (c.getUser().getUserId() == id)
                cats.add(c);

        LinkedList<SmFile> files1 = new LinkedList<SmFile>();

        for (SmCategory c : cats)
            files1.addAll(c.getFiles());
        return files1;
    }

    public LinkedList<SmFile> getAllFilesFromCategory(Long categoryId)
    {
        SmCategory cat = new SmCategory();

        for (SmCategory c : CategoryDAOMock.getcats())
            if (c.getCategoryId() == categoryId)
            {
                cat = c;
                break;
            }
        return cat.getFiles();// LinkedList<SmCategory> cats=cat.getAllCategoriesOfUser(userName);


    }

    public void add(SmFile newFile)
    {
        files.add(newFile);
    }

    public void deleteById(Long fileId)
    {

        for (SmFile f : files)
            if (f.getFileId() == fileId)
                files.remove(f);
    }
}
