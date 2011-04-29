package qbix.sm.client.beans;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 *
 * @author iliax
 */

//потом добавим аннотации хибера
public class User implements IsSerializable,BeanModelTag {

   private Long userId;

   private String name;

   private String email;

   private String password;

   private String path;

    public User() {
    }

    public User(Long id, String name, String email, String password, String path) {
        this.userId = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //при необходимости переписать!
    @Override
    public boolean equals(Object obj) {
        if (obj==this)
            return true;
        if(obj instanceof User){
            User objUser=(User)obj;
            if(objUser.getUserId().equals(userId)==true)
                return true;
            if(objUser.getName().equals(name) && objUser.getPassword().equals(password)==true)
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.userId != null ? this.userId.hashCode() : 0);
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 97 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return userId+" "+name;
    }

}
