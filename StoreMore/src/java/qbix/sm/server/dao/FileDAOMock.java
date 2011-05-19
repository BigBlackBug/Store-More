/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.server.dao;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import qbix.sm.client.beans.SmCategory;
import qbix.sm.client.beans.SmFile;

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
        return  new LinkedList<SmFile>();
//        LinkedList<SmFile> q = new LinkedList<SmFile>();
//        q.add(new SmFile(8L, null, "R", "P", 7L, null));
//        return q;//
        // return files;
    }

    public LinkedList<SmFile> getAllFilesOfUser(String userName)
    {
        // UserDao userDao=new UserDAOImpl();
        //User user=userDao.getByName(userName);
        LinkedList<SmFile> q = new LinkedList<SmFile>();

        q.add(new SmFile(1L, null, "realname", "C://123.exe", 123456L, Calendar.getInstance().getTime()));
        q.add(new SmFile(1L, null, "realname2", "C://123.exe", 123456L, null));
        q.add(new SmFile(1L, null, "realname3", "C://123.exe", 123456L, null));
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
        if (categoryId %2==0)
        {
            LinkedList<SmFile> q = new LinkedList<SmFile>();

            q.add(new SmFile(1L, null, "realname", "C://123.exe", 123456L, Calendar.getInstance().getTime()));
            q.add(new SmFile(1L, null, "realname2", "C://123.exe", 123456L, Calendar.getInstance().getTime()));
            return q;
        } else
        {
            LinkedList<SmFile> q = new LinkedList<SmFile>();

            q.add(new SmFile(1L, null, "realnameQ", "C://345.exe", 123456L, Calendar.getInstance().getTime()));
            q.add(new SmFile(1L, null, "realnameQQ", "C://345.exe", 123456L, Calendar.getInstance().getTime()));
            q.add(new SmFile(1L, null, "realnameQQQ", "C://345.exe", 123456L, Calendar.getInstance().getTime()));
            return q;
        }
        /*SmCategory cat = new SmCategory();

        for (SmCategory c : CategoryDAOMock.getcats())
        if (c.getCategoryId() == categoryId)
        {
        cat = c;
        break;
        }
        return cat.getFiles();// LinkedList<SmCategory> cats=cat.getAllCategoriesOfUser(userName);*/


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

    public void delete(SmFile fileToDelete)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
