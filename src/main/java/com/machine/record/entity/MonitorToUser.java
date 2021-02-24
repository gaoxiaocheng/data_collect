package com.machine.record.entity;

import javax.persistence.*;

/**
 * 异常推送人员表
 */
@Entity
@Table(name = "m_touser")
public class MonitorToUser {

    private int id;
    private String project;
    private String touser;
    private String status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
