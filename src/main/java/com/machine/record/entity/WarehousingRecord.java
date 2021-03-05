package com.machine.record.entity;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="warehousing_record")
public class WarehousingRecord  extends BaseEntity {
    //入库时间
    private Date storageTime;

    //入库接受人
    private String storageBy;

    //存储地点编号
    private String addressCode;

    //出库时间
    private Date deliveryTime;

    //出库确认人
    private String deliveryBy;

    //出库接收人
    private String reciveBy;

    public Date getStorageTime() {
        return storageTime;
    }

    public void setStorageTime(Date storageTime) {
        this.storageTime = storageTime;
    }

    public String getStorageBy() {
        return storageBy;
    }

    public void setStorageBy(String storageBy) {
        this.storageBy = storageBy;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryBy() {
        return deliveryBy;
    }

    public void setDeliveryBy(String deliveryBy) {
        this.deliveryBy = deliveryBy;
    }

    public String getReciveBy() {
        return reciveBy;
    }

    public void setReciveBy(String reciveBy) {
        this.reciveBy = reciveBy;
    }
}
