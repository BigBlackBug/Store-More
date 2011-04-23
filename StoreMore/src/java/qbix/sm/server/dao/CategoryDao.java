package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.SmCategory;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */

public interface CategoryDao {

    //for testing only!
    LinkedList<SmCategory> getAll();

    SmCategory getById(Long categoryId);
    
    LinkedList<SmCategory> getAllCategoriesOfUserById(Long userId);
    
    LinkedList<SmCategory> getAllCategoriesOfUser(String userName);

    //категория уже имеет ссылки на родителя и юзера!(все обертки делаются в сервисе!)
    void add(SmCategory newCategory);

    void deleteById(Long categoryId);

    void deleteAllCategoriesOfUserById(Long userId);

    void deleteAllCategoriesOfUser(String userName);
}
