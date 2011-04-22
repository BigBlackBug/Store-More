package qbix.sm.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;


/**
 *
 * @author iliax
 */
public class SmFile implements  IsSerializable{


    private Long fileId;

    private SmCategory category;

    //name of user's file
    private String realName;

    //server file name
    private String generatedName;

    //in Kb
    private Long size;

    //maybe use SQL Date type or TimeStamp here...
    private Date uploadDate;

    public SmFile() {
    }

    public SmFile(Long id, SmCategory category, String realName, String pathName, Long size, Date uploadDate) {
        this.fileId = id;
        this.category = category;
        this.realName = realName;
        this.generatedName = pathName;
        this.size = size;
        this.uploadDate = uploadDate;
    }

    public Long getFileId() {
        return fileId;
    }

   

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPathName() {
        return generatedName;
    }

    public void setPathName(String pathName) {
        this.generatedName = pathName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return fileId+" "+realName+" categId:"+getCategory().getCategoryId();
    }

    /**
     * @return the category
     */
    public SmCategory getCategory()
    {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(SmCategory category)
    {
        this.category = category;
    }


}
