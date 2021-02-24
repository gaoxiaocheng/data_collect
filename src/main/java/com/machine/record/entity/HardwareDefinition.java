package com.machine.record.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="hardward_definition")
public class HardwareDefinition extends BaseEntity {

    //名称
    private String name;
    //种类编码
    private String type;
    //生产厂商
    private String manufacturer;
    //出厂时间
    private String deliveryTime;
    //出厂负责人
    private String productionPeopleInCharge;
    //上市时间
    private String marketTime;
    //上市负责人
    private String marketPersonInCharge;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getProductionPeopleInCharge() {
        return productionPeopleInCharge;
    }

    public void setProductionPeopleInCharge(String productionPeopleInCharge) {
        this.productionPeopleInCharge = productionPeopleInCharge;
    }

    public String getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(String marketTime) {
        this.marketTime = marketTime;
    }

    public String getMarketPersonInCharge() {
        return marketPersonInCharge;
    }

    public void setMarketPersonInCharge(String marketPersonInCharge) {
        this.marketPersonInCharge = marketPersonInCharge;
    }
}
