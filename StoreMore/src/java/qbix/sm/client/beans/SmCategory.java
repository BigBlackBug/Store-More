package qbix.sm.client.beans;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author iliax
 */
public class SmCategory implements IsSerializable,BeanModelTag{

    private Long categoryId;

    private SmCategory parentCategory;

    private User user;

    private String name;

    private String password;

    private String description;

    private LinkedList<SmFile> files=new LinkedList<SmFile>();

    private HashSet<SmCategory> children=new HashSet<SmCategory>();

    public SmCategory() {
    }

    public SmCategory(Long id, SmCategory parentCategory, String name, String password, String description) {
        this.categoryId = id;
        this.parentCategory = parentCategory;
        this.name = name;
        this.password = password;
        this.description = description;
       
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long id) {
        this.categoryId = id;
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
            if(objCat.getCategoryId().equals(getCategoryId()))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.getCategoryId() != null ? this.getCategoryId().hashCode() : 0);
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return getCategoryId()+", "+name+", parentId:"+parentCategory.getCategoryId()+"subCategories:"+children.toString()+",  files:"+files.toString();
    }

    /**
     * @return the parentCategory
     */
    public SmCategory getParentCategory()
    {
        return parentCategory;
    }

    /**
     * @param parentCategory the parentCategory to set
     */
    public void setParentCategory(SmCategory parentCategory)
    {
        this.parentCategory = parentCategory;
    }

    /**
     * @return the user
     */
    public User getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user)
    {
        this.user = user;
    }
}
