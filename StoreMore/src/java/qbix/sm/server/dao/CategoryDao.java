package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.SmCategory;

/**
 *
 * @author iliax
 */
public interface CategoryDao {

    //not a useful method
    SmCategory getById(Long categoryId);
    //берёт корневые категории. без вложенных.
    LinkedList<SmCategory> getByUserId(Long userId);
    
    LinkedList<SmCategory> getByUsername(String userName);
    
    LinkedList<SmCategory> getNestedCategories(Long categoryId);

    void add(String userName,SmCategory parent,SmCategory newCategory);

}
