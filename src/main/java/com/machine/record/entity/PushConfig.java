package com.machine.record.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 微信推送的接口配置
 */
@Entity
@Table(name = "m_push_config")
public class PushConfig implements Serializable {
    private int pushId;
    private String pushUrl;
    private String pushMethod;
    private String pushType;
    private String remark;
    private String pushSecret;
    private String pushAgentid;

    public String getPushSecret() {
        return pushSecret;
    }

    public void setPushSecret(String pushSecret) {
        this.pushSecret = pushSecret;
    }

    public String getPushAgentid() {
        return pushAgentid;
    }

    public void setPushAgentid(String pushAgentid) {
        this.pushAgentid = pushAgentid;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getPushId() {
        return pushId;
    }

    public void setPushId(int pushId) {
        this.pushId = pushId;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public String getPushMethod() {
        return pushMethod;
    }

    public void setPushMethod(String pushMethod) {
        this.pushMethod = pushMethod;
    }
}
