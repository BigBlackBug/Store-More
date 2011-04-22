package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.SmCategory;

/**
 *
 * @author iliax
 */

public interface CategoryDao {

    //for testing only!
    LinkedList<SmCategory> getAll();

    SmCategory getById(Long categoryId);
    
    LinkedList<SmCategory> getByUserId(Long userId);
    
    LinkedList<SmCategory> getByUserName(String userName);

    //категория уже имеет ссылки на родителя и юзера!(все обертки делаются в сервисе!)
    void add(SmCategory newCategory);

    void deleteById(Long categoryId);

    void deleteAllByUserId(Long userId);
}
