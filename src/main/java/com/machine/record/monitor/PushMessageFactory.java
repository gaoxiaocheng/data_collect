package com.machine.record.monitor;

import com.machine.record.entity.MonitorConfig;
import com.machine.record.entity.PushConfig;
import com.machine.record.monitor.inter.PushMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PushMessageFactory {
    @Resource
//    @Qualifier("")
    private PushMessage sanYuanNginxPush;

    @Resource
//    @Qualifier("")
    private PushMessage wxPush;
    @Resource
//    @Qualifier("")
    private PushMessage commonPush;

    public PushMessage getPushClass(MonitorConfig monitorConfig) {
        PushConfig pushConfig = monitorConfig.getPushConfig();
        if (pushConfig == null) {
            //默认
            pushConfig = new PushConfig();
            pushConfig.setPushId(1);
            pushConfig.setPushUrl("http://10.2.2.71:8080/urltrans/urltrans");
            pushConfig.setPushType("sanYuanNginx");
            pushConfig.setPushMethod("get");
            monitorConfig.setPushConfig(pushConfig);
            return getPushClass(monitorConfig);
        }
        //选择器
        if ("sanyuannginx".equals(pushConfig.getPushType().toLowerCase()) && "get".equals(pushConfig.getPushMethod().toLowerCase())) {
            return sanYuanNginxPush;
        }
        if ("wxpush".equals(pushConfig.getPushType().toLowerCase())) {
            return wxPush;
        }
        if ("common".equals(pushConfig.getPushType().toLowerCase())) {
            return commonPush;
        }


        //配置错误，默认用sanyuannginx类
        pushConfig = null;
        monitorConfig.setPushConfig(pushConfig);
        return getPushClass(monitorConfig);

    }
}
