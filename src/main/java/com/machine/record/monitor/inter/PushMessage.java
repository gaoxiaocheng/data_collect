package com.machine.record.monitor.inter;

import com.machine.record.entity.MonitorConfig;

public interface PushMessage {
    void push(String message, String touser, MonitorConfig monitorConfig);
}
