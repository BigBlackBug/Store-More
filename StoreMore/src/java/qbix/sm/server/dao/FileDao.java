package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */
public interface  FileDao {

    //for testing only!
    LinkedList<SmFile> getAll();

    LinkedList<SmFile> getAllByUserName(User userName);

    LinkedList<SmFile> getAllByUserId(Long id);

    LinkedList<SmFile> getByCategoryId(Long categoryId);

    void add(SmFile newFile);

    void deleteById(Long fileId);
}
