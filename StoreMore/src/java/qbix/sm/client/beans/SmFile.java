package qbix.sm.client.beans;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Date;


/**
 *
 * @author iliax
 */
public class SmFile implements  IsSerializable{

    private Long fileId;

    private Long id;

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
        this.id = id;
        this.catId = catId;
        this.realName = realName;
        this.pathName = pathName;
        this.size = size;
        this.uploadDate = uploadDate;
    }

>>>>>>>>>>>>>>>>>>>> File 1
>>>>>>>>>>>>>>>>>>>> File 2
<<<<<<< Temporary merge branch 1
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
=======
    public Long getFileId() {
        return fileId;
    }
>>>>>>>>>>>>>>>>>>>> File 3
<<<<<<< HEAD
    public Long getFileId() {
        return fileId;
    }
<<<<<<<<<<<<<<<<<<<<

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return id+" "+realName+" categId:"+catId;
    }


}
