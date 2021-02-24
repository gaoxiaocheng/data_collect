package com.machine.record.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 通过现成的webservice接口查询mdx语句的webservice配置
 */
@Entity
@Table(name = "m_webservice")
public class CubeWebServiceConfig implements Serializable {
    private Integer webId;
    private String code;
    private String endpoint;
    private String nameSpace;
    private String webMethod;
    private String status;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getWebId() {
        return webId;
    }

    public void setWebId(Integer webId) {
        this.webId = webId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getWebMethod() {
        return webMethod;
    }

    public void setWebMethod(String webMethod) {
        this.webMethod = webMethod;
    }
}
