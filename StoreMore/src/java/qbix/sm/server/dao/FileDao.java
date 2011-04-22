package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */
public interface  FileDao {

    LinkedList<SmFile> getAllByUsersNickname(User nickName);

    LinkedList<SmFile> getAllByUsersId(Long id);

    LinkedList<SmFile> getByCategoryId(Long userId,Long categoryId);
    
    LinkedList<SmFile> getByCategoryId(String userName,Long categoryId);

    void add(SmFile newFile);
}
