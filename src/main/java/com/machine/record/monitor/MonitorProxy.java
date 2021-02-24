package com.machine.record.monitor;

import com.machine.record.entity.MonitorConfig;
import com.machine.record.monitor.inter.Monitor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public final class MonitorProxy {

    @Resource
    MonitrorFacory monitrorFacory;

    public void monitor(MonitorConfig monitorConfig) {
        Monitor monitor = monitrorFacory.getMonitor(monitorConfig);
        if (monitor != null) {
            monitor.sendMessage(monitorConfig);
        }
    }
}
