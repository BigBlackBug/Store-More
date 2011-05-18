/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.server.dao;

import java.util.Arrays;
import java.util.LinkedList;
import qbix.sm.client.beans.SmCategory;

/**
 *
 * @author BigBlackBug
 */
public class CategoryDAOMock implements CategoryDao
{
    private static LinkedList<SmCategory> categories = new LinkedList<SmCategory>();

    public CategoryDAOMock()
    {
    }

    public static LinkedList<SmCategory> getcats()
    {
        return categories;
    }

    public LinkedList<SmCategory> getAll()
    {
      
      Long id = 1L;
        SmCategory cat1 = new SmCategory(id++, "nameRoot", null, "desc1");
        cat1.add(new SmCategory(id++, "nameIn", "passIn", "descIn"));
        SmCategory cat2 = new SmCategory(id++, "name11111", "pass1", "desc1");
        SmCategory cat3 = new SmCategory(id++, "nameQ1", "pass1", "desc1");
        SmCategory cat4 = new SmCategory(id++, "nameQ2", "pass1", "desc1");

        SmCategory cat5 = new SmCategory(id++, "t2y", "pass1", "desc1");

        SmCategory cat6 =
                new SmCategory(id++, "nameQ3", "pass1", "desc1",
                new SmCategory[]
                {
                    new SmCategory(id++, "qw", "pass1", "desc1"),
                    new SmCategory(id++, "er", "pass1", "desc1"),
                    new SmCategory(id++, "ty", "pass1", "desc1")
                });

        cat4.add(cat5);
        //cat5.setParent(cat4);
        cat4.add(cat6); cat3.add(cat4);

        LinkedList<SmCategory> cats = new LinkedList<SmCategory>();
        cats.add(cat1);cats.add(cat2);cats.add(cat3);
        cats.add(cat6);cats.add(cat5);cats.add(cat4);
        return cats;
    }

    public SmCategory getById(Long categoryId)
    {
        /*SmCategory cat = new SmCategory();

        for (SmCategory c : categories)
            if (c.getCategoryId() == categoryId)
            {
                cat = c;
                break;
            }
        return cat;*/ 
        SmCategory cat1 = new SmCategory(1L, "nameRoot", "qwe", "desc1");
        return cat1;
        

    }

    public LinkedList<SmCategory> getAllCategoriesOfUserById(Long userId)
    {
         Long id = 1L;
        SmCategory cat1 = new SmCategory(id++, "nameRoot", "pass", "desc1");
        cat1.add(new SmCategory(id++, "nameIn", "passIn", "descIn"));
        SmCategory cat2 = new SmCategory(id++, "name1", "pass1", "desc1");
        SmCategory cat3 = new SmCategory(id++, "nameQ1", "pass1", "desc1");
        SmCategory cat4 = new SmCategory(id++, "nameQ2", "pass1", "desc1");

        SmCategory cat5 = new SmCategory(id++, "t2y", "pass1", "desc1");

        SmCategory cat6 =
                new SmCategory(id++, "nameQ3", "pass1", "desc1",
                new SmCategory[]
                {
                    new SmCategory(id++, "qw", "pass1", "desc1"),
                    new SmCategory(id++, "er", "pass1", "desc1"),
                    new SmCategory(id++, "ty", "pass1", "desc1")
                });

        cat4.add(cat5);
        //cat5.setParent(cat4);
        cat4.add(cat6); cat3.add(cat4);

        LinkedList<SmCategory> cats = new LinkedList<SmCategory>();
        cats.add(cat1);cats.add(cat2);cats.add(cat3);
        cats.add(cat6);cats.add(cat5);cats.add(cat4);
        return cats;
        /*LinkedList<SmCategory> result = new LinkedList<SmCategory>();//SmCategory cat = new SmCategory();

        for (SmCategory c : categories)
            if (c.getUser().getUserId() == userId)
                result.add(c);

        return result;*/
    }

    public LinkedList<SmCategory> getAllCategoriesOfUser(String userName)
    {
        LinkedList<SmCategory> result = new LinkedList<SmCategory>();//SmCategory cat = new SmCategory();

        for (SmCategory c : categories)
            if (c.getUser().getName().equals(userName))
                result.add(c);

        return result;
    }

    public void add(SmCategory newCategory)
    {
        categories.add(newCategory);
    }

    //Следующие три метода мего крабские, но что ж поделаешь. Заглушка.
    public void deleteById(Long categoryId)
    {
        categories.remove(getById(categoryId));

    }

    public void deleteAllCategoriesOfUserById(Long userId)
    {
        categories.remove(getAllCategoriesOfUserById(userId));

    }

    public void deleteAllCategoriesOfUser(String userName)
    {
        categories.remove(getAllCategoriesOfUser(userName));
    }
}
