package qbix.sm.client.beans;

import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.LinkedList;

/**
 *
 * @author iliax
 */
public class SmCategory extends BaseTreeModel implements IsSerializable
{
    private LinkedList<SmFile> files = new LinkedList<SmFile>();

    public SmCategory()
    {
    }

    public SmCategory(Long id, String name, String password, String description)
    {
        set("categoryId", id);
        set("name", name);
        if (password == null)
            set("password", "");
        else
            set("password", password);
        set("description", description);

    }

    public SmCategory(Long id, String name, String password, String description, SmCategory[] children)
    {
        set("categoryId", id);
        //setParent(parentCategory);
        set("name", name);
        set("password", password);
        set("description", description);
        for (int i = 0; i < children.length; i++)
            add(children[i]);
    }

    public SmCategory(Long id, SmCategory parentCategory, String name, String password, String description)
    {
        set("categoryId", id);
        setParent(parentCategory);
        // parentCategory.add(this);
        set("name", name);
        set("password", password);
        set("description", description);

    }

    public SmCategory(Long id, SmCategory parentCategory, String name, String password, String description, BaseTreeModel[] children)
    {
        this(id, parentCategory, name, password, description);
        for (int i = 0; i < children.length; i++)
            add(children[i]);
    }

    public boolean hasPassword()
    {
        return !((String) get("password")).equals("");
    }

    public Long getCategoryId()
    {
        return (Long) get("categoryId");
    }

    public void setCategoryId(Long id)
    {
        set("categoryId", id);
    }

    public String getName()
    {
        return (String) get("name");
    }

    public void setName(String name)
    {
        set("name", name);
    }

    public String getPassword()
    {
        return (String) get("password");
    }

    public void setPassword(String password)
    {
        set("password", password);
    }

    public String getDescription()
    {
        return (String) get("description");
    }

    public void setDescription(String description)
    {
        set("description", description);
    }

    public LinkedList<SmFile> getFiles()
    {
        return (LinkedList<SmFile>) get("files");
    }

    public void setFiles(LinkedList<SmFile> files)
    {
        set("files", files);
    }

    //при неоходимости переписать!
    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (obj instanceof SmCategory)
        {
            SmCategory objCat = (SmCategory) obj;
            if (objCat.getCategoryId().equals(getCategoryId()))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 53 * hash + (this.getCategoryId() != null ? this.getCategoryId().hashCode() : 0);
        hash = 53 * hash + (getName() != null ? getName().hashCode() : 0);
        hash = 53 * hash + (getPassword() != null ? getPassword().hashCode() : 0);
        return hash;
    }

    @Override
    public String toString()
    {
        return getCategoryId() + ", " + getName() + ", parentId:"
                + ((SmCategory) getParent()).getCategoryId() + "subCategories:"
                + children.toString() + ",  files:" + files.toString();
    }

    public SmCategory getParentCategory()
    {
        return (SmCategory) getParent();
    }

    public void setParentCategory(SmCategory parentCategory)
    {
        set("parentCategory", parentCategory);
        setParent((TreeModel) parentCategory);
    }

    /**
     * @return the user
     */
    public User getUser()
    {
        return (User) get("user");
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user)
    {
        set("user", user);
    }
}
