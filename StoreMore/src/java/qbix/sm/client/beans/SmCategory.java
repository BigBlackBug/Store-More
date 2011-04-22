package qbix.sm.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author iliax
 */
public class SmCategory implements IsSerializable{

    private Long id;

    private Long parentId;

    private Long userId;

    private String name;

    private String password;

    private String description;

    private LinkedList<SmFile> files;

    private HashSet<SmCategory> children;

    public SmCategory() {
    }

    public SmCategory(Long id, Long parentId, String name, String password, String description, LinkedList<SmFile> files, HashSet<SmCategory> childs) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.password = password;
        this.description = description;
        this.files = files;
        this.children = childs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashSet<SmCategory> getChildren() {
        return children;
    }

    public void setChildren(HashSet<SmCategory> childs) {
        this.children = childs;
    }

    public LinkedList<SmFile> getFiles() {
        return files;
    }

    public void setFiles(LinkedList<SmFile> files) {
        this.files = files;
    }

    //при неоходимости переписать!
    @Override
    public boolean equals(Object obj) {
        if(obj==this)
            return true;
        if(obj instanceof SmCategory){
            SmCategory objCat=(SmCategory)obj;
            if(objCat.getId().equals(id))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return id+", "+name+", parentId:"+parentId+"subCategories:"+children.toString()+",  files:"+files.toString();
    }
}
