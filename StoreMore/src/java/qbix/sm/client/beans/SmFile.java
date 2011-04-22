package qbix.sm.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;


/**
 *
 * @author iliax
 */
public class SmFile implements  IsSerializable{
<<<<<<< HEAD
    private Long fileId;
=======
    private Long id;
>>>>>>> origin/dev_iliax

    private Long catId;

    //name of user's file
    private String realName;

    //server file name
    private String pathName;

    //in Kb
    private Long size;

    //maybe use SQL Date type or TimeStamp here...
    private Date uploadDate;

    public SmFile() {
    }

    public SmFile(Long id, Long catId, String realName, String pathName, Long size, Date uploadDate) {
<<<<<<< HEAD
        this.fileId = id;
=======
        this.id = id;
>>>>>>> origin/dev_iliax
        this.catId = catId;
        this.realName = realName;
        this.pathName = pathName;
        this.size = size;
        this.uploadDate = uploadDate;
    }

<<<<<<< HEAD
    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long id) {
        this.fileId = id;
=======
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
>>>>>>> origin/dev_iliax
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
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
<<<<<<< HEAD
        return fileId+" "+realName+" categId:"+catId;
=======
        return id+" "+realName+" categId:"+catId;
>>>>>>> origin/dev_iliax
    }


}
