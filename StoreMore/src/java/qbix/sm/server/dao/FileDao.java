package qbix.sm.server.dao;

import java.util.LinkedList;
import org.aspectj.lang.annotation.Aspect;
import qbix.sm.client.beans.SmFile;
import qbix.sm.client.beans.User;

/**
 *
 * @author iliax
 */

public interface  FileDao {

    //for testing only!
    LinkedList<SmFile> getAll();

    LinkedList<SmFile> getAllFilesOfUser(String userName);

    LinkedList<SmFile> getAllFilesOfUserById(Long id);

    LinkedList<SmFile> getAllFilesFromCategory(Long categoryId);

    void add(SmFile newFile);

    void deleteById(Long fileId);
}
