package qbix.sm.server.dao;

import java.util.LinkedList;
import qbix.sm.client.beans.SmFile;

/**
 *
 * @author iliax
 */
public interface  FileDao {

    LinkedList<SmFile> getAll();

    LinkedList<SmFile> getByCatId(Long categoryId);

    void add(SmFile newFile);
}
