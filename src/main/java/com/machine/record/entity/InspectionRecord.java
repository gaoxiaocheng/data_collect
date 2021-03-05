package com.machine.record.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name="inspection_record")
public class InspectionRecord extends BaseEntity {

    //合格程度  特别严重，严重，中度，普通问题，合格，良，优秀
    private int isQualified;

    //检测类型
    private String type;

    //检测时间
    private Date testTime;

    //检测人
    private String testBy;

    //确认时间
    private Date confirmationTime;

    //确认人
    private String confirmationBy;

    public int getIsQualified() {
        return isQualified;
    }

    public void setIsQualified(int isQualified) {
        this.isQualified = isQualified;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public String getTestBy() {
        return testBy;
    }

    public void setTestBy(String testBy) {
        this.testBy = testBy;
    }

    public Date getConfirmationTime() {
        return confirmationTime;
    }

    public void setConfirmationTime(Date confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    public String getConfirmationBy() {
        return confirmationBy;
    }

    public void setConfirmationBy(String confirmationBy) {
        this.confirmationBy = confirmationBy;
    }
}
