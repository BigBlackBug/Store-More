package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.SmCategory;

/**
 *
 * @author iliax
 */
public interface CategoryDao {

    LinkedList<SmCategory> getAll();

    SmCategory getById(Long categoryId);

    LinkedList<SmCategory> getByUserId(Long userId);

    LinkedList<SmCategory> getByUsername(String userName);

    void add(SmCategory newCategory);

}
