/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.server.dao;

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
        return categories;
    }

    public SmCategory getById(Long categoryId)
    {
        SmCategory cat = new SmCategory();

        for (SmCategory c : categories)
            if (c.getCategoryId() == categoryId)
            {
                cat = c;
                break;
            }
        return cat;

    }

    public LinkedList<SmCategory> getAllCategoriesOfUserById(Long userId)
    {
        LinkedList<SmCategory> result = new LinkedList<SmCategory>();//SmCategory cat = new SmCategory();
       
        for (SmCategory c : categories)
            if (c.getUser().getUserId() == userId)
                result.add(c);

        return result;
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
