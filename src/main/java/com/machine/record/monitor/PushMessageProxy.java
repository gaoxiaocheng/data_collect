package com.machine.record.monitor;

import com.machine.record.entity.MonitorConfig;
import com.machine.record.monitor.inter.PushMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PushMessageProxy {
    @Resource
    PushMessageFactory pushMessageFactory;

    public void pushMessage(String message, String touser, MonitorConfig monitorConfig){
        PushMessage pushMessage = pushMessageFactory.getPushClass(monitorConfig);
        pushMessage.push(message,touser,monitorConfig);
    }
}
