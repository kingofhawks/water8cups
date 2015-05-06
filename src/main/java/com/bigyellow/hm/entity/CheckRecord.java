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
public class CheckRecord {
    @Id
    private int FrecordID;
    private String Fbiller;
//    private int FbillerID;
    private String FAPPBiller;
    private String BillName;
    private String Billno;
    private String FDescription;
    private String RequestDate;
    private String Checkstatus;

    public String getBillName() {
        return BillName;
    }

    public void setBillName(String billName) {
        BillName = billName;
    }

    public String getBillno() {
        return Billno;
    }

    public void setBillno(String billno) {
        Billno = billno;
    }

    public String getCheckstatus() {
        return Checkstatus;
    }

    public void setCheckstatus(String checkstatus) {
        Checkstatus = checkstatus;
    }

    public String getFAPPBiller() {
        return FAPPBiller;
    }

    public void setFAPPBiller(String FAPPBiller) {
        this.FAPPBiller = FAPPBiller;
    }

    public String getFbiller() {
        return Fbiller;
    }

    public void setFbiller(String fbiller) {
        Fbiller = fbiller;
    }

//    public int getFbillerID() {
//        return FbillerID;
//    }
//
//    public void setFbillerID(int fbillerID) {
//        FbillerID = fbillerID;
//    }

    public String getFDescription() {
        return FDescription;
    }

    public void setFDescription(String FDescription) {
        this.FDescription = FDescription;
    }

    public int getFrecordID() {
        return FrecordID;
    }

    public void setFrecordID(int frecordID) {
        FrecordID = frecordID;
    }

    public String getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(String requestDate) {
        RequestDate = requestDate;
    }

    @Override
    public String toString() {
        return "CheckRecord{" +
                "BillName='" + BillName + '\'' +
                ", FrecordID=" + FrecordID +
                ", Fbiller='" + Fbiller + '\'' +
                ", FAPPBiller='" + FAPPBiller + '\'' +
                ", Billno='" + Billno + '\'' +
                ", FDescription='" + FDescription + '\'' +
                ", RequestDate='" + RequestDate + '\'' +
                ", Checkstatus='" + Checkstatus + '\'' +
                '}';
    }
}
