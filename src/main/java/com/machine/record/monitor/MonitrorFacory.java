package com.machine.record.monitor;

import com.machine.record.entity.MonitorConfig;
import com.machine.record.monitor.inter.Monitor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public final class MonitrorFacory {
    @Resource
    Monitor wechatRealTimeMonitor;
    @Resource
    Monitor commonCubeWebMonitor;
    @Resource
    Monitor commonSqlMonitor;
    @Resource
    Monitor commonUrlMonitor;

    public Monitor getMonitor(MonitorConfig monitorConfig) {
        String project = monitorConfig.getProject();
        String monitorType = monitorConfig.getMonitorType().toLowerCase();
        String type = monitorConfig.getType();
        if ("wechat".equals(project.toLowerCase()) && "realtime".equals(monitorType.toLowerCase()) && !"url".equals(type.toLowerCase())) {
            return wechatRealTimeMonitor;
        } else if ("wechat".equals(project.toLowerCase()) && "history".equals(monitorType.toLowerCase()) && "cubeweb".equals(type.toLowerCase())) {
            return commonCubeWebMonitor;
        } else if (StringUtils.isNotBlank(monitorType) && monitorType.toLowerCase().contains("commonsql")) {
            return commonSqlMonitor;
        }
        else if (StringUtils.isNotBlank(monitorType) && monitorType.toLowerCase().contains("commoncube")) {
            return commonCubeWebMonitor;
        }else if (StringUtils.isNotBlank(monitorType) && monitorType.toLowerCase().contains("commonurl")) {
            return commonUrlMonitor;
        }

        return null;
    }

}
