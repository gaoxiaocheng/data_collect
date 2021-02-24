package com.machine.record.entity;

import javax.persistence.*;

/**
 * 监控配置主表
 */
@Entity
@Table(name = "m_config")
public class MonitorConfig {

    private int id;
    private String type;
    private String project;
    private String monitorType;
    private String statCode;
    private String database;
    private String dataUser;
    private String dataPassword;
    private String topic;
    private String url;
    private String param;
    private String method;
    private String resultStatus;
    private String monitorHql;
    private Double workDayValue;
    private Double holyDayValue;
    private Double workDayThresholdValue;
    private Double holyDayThresholdValue;
    private String message;
    private String cubeWebserviceCode;
    private String cubeName;
    private String dim;
    private PushConfig pushConfig;
    private int pushConfigId;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pushConfigId", insertable = false, updatable = false)
    public PushConfig getPushConfig() {
        return pushConfig;
    }

    public void setPushConfig(PushConfig pushConfig) {
        this.pushConfig = pushConfig;
    }

    public int getPushConfigId() {
        return pushConfigId;
    }

    public void setPushConfigId(int pushConfigId) {
        this.pushConfigId = pushConfigId;
    }

    public void setWorkDayValue(Double workDayValue) {
        this.workDayValue = workDayValue;
    }

    public void setHolyDayValue(Double holyDayValue) {
        this.holyDayValue = holyDayValue;
    }

    public void setWorkDayThresholdValue(Double workDayThresholdValue) {
        this.workDayThresholdValue = workDayThresholdValue;
    }

    public void setHolyDayThresholdValue(Double holyDayThresholdValue) {
        this.holyDayThresholdValue = holyDayThresholdValue;
    }

    public String getCubeWebserviceCode() {
        return cubeWebserviceCode;
    }

    public void setCubeWebserviceCode(String cubeWebserviceCode) {
        this.cubeWebserviceCode = cubeWebserviceCode;
    }

    public String getCubeName() {
        return cubeName;
    }

    public void setCubeName(String cubeName) {
        this.cubeName = cubeName;
    }

    public String getDim() {
        return dim;
    }

    public void setDim(String dim) {
        this.dim = dim;
    }

    private CubeWebServiceConfig cubeWebServiceConfig;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cubeWebserviceCode", referencedColumnName = "code", insertable = false, updatable = false)
    public CubeWebServiceConfig getCubeWebServiceConfig() {
        return cubeWebServiceConfig;
    }

    public void setCubeWebServiceConfig(CubeWebServiceConfig cubeWebServiceConfig) {
        this.cubeWebServiceConfig = cubeWebServiceConfig;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getMonitorType() {
        return monitorType;
    }

    public void setMonitorType(String monitorType) {
        this.monitorType = monitorType;
    }

    public String getStatCode() {
        return statCode;
    }

    public void setStatCode(String statCode) {
        this.statCode = statCode;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDataUser() {
        return dataUser;
    }

    public void setDataUser(String dataUser) {
        this.dataUser = dataUser;
    }

    public String getDataPassword() {
        return dataPassword;
    }

    public void setDataPassword(String dataPassword) {
        this.dataPassword = dataPassword;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMonitorHql() {
        return monitorHql;
    }

    public void setMonitorHql(String monitorHql) {
        this.monitorHql = monitorHql;
    }

    public Double getWorkDayValue() {
        return workDayValue;
    }

    public Double getHolyDayValue() {
        return holyDayValue;
    }

    public Double getWorkDayThresholdValue() {
        return workDayThresholdValue;
    }

    public Double getHolyDayThresholdValue() {
        return holyDayThresholdValue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
