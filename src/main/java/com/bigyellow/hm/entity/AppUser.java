package com.bigyellow.hm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date: 15-4-2
 * Time: 下午8:28
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class AppUser {
    @Id
    private int fid;
    private String fuser;
    private String fphone;
    private int fk3UserId;
    private int fpassword;
    private String fMenuList;
    private int fStatus;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFuser() {
        return fuser;
    }

    public void setFuser(String fuser) {
        this.fuser = fuser;
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public int getFk3UserId() {
        return fk3UserId;
    }

    public void setFk3UserId(int fk3UserId) {
        this.fk3UserId = fk3UserId;
    }

    public int getFpassword() {
        return fpassword;
    }

    public void setFpassword(int fpassword) {
        this.fpassword = fpassword;
    }

    public String getfMenuList() {
        return fMenuList;
    }

    public void setfMenuList(String fMenuList) {
        this.fMenuList = fMenuList;
    }

    public int getfStatus() {
        return fStatus;
    }

    public void setfStatus(int fStatus) {
        this.fStatus = fStatus;
    }
}
