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
public class NewClass
{
    public static void main(String[] args)
    {
        UserDAOMock udao = new UserDAOMock();
        CategoryDAOMock cdao = new CategoryDAOMock();
        FileDAOMock fdao = new FileDAOMock();

        User user = new User(1l, "bigblackbug", "email1", "pass1", "path1");

        SmCategory cat = new SmCategory(1L, null, "catname1", "catpass1", "description1");
        SmCategory innercat=new SmCategory(2L, cat,"innercat1", "pass2", "desc123");

        SmFile file = new SmFile(1L, cat, "realname1", "generated1", 777L, null);

        cat.setUser(user);
        
        cat.getFiles().add(file);

        cdao.add(innercat);
        cdao.add(cat);
        udao.add(user);
        fdao.add(file);

       /*for(SmFile f:fdao.files)
            System.out.println(f.getRealName());

       for(SmCategory c:cdao.categories)
            System.out.println(c.getName()+" "+c.getUser().getName()+c.getFiles().peek().getRealName());*/

        LinkedList<SmFile> files=fdao.getAllFilesOfUser("bigblackbug");

        for(SmFile f:files)
            System.out.println(f.getRealName());



    }
}
