package com.machine.record.monitor.impl;

import com.machine.record.dao.MonitorToUserDao;
import com.machine.record.entity.MonitorConfig;
import com.machine.record.monitor.PushMessageProxy;
import com.machine.record.monitor.inter.Monitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("commonSqlMonitor")
public class CommonSqlMonitor implements Monitor {
    @Autowired
    PushMessageProxy pushMessageProxy;
    @Resource
    MonitorToUserDao monitorToUserDao;


    @Override
    public String getTouser(String project) {
        return monitorToUserDao.getTouser(project);
    }

    @Override
    public String statistics(MonitorConfig monitorConfig) {
        Double value = getValueForCommonSql(monitorConfig);
        //获取估值和阈值
        Map<String, Double> map = getCommonThresholdForsql(monitorConfig);
        Double old_value = map.get("old_value");
        Double diff_allow = map.get("diff_allow");
        Double diff = Math.abs(old_value - value);
        if (diff > diff_allow) {
            String project = monitorConfig.getProject();
            if ("monitorself".equals(project.toLowerCase())) {
                return monitorConfig.getMessage();
            } else {
                if (old_value != null) {
                    Double prcent = (double) Math.round((diff * 100) / old_value);
                    return monitorConfig.getMessage() + ":当前指标值为" + value + ",预设值为" + old_value + ",差距为" + diff + "，差值比例为 " + prcent + "%" ;
                }
            }
        }
        return null;
    }


    @Override
    public void push(String message, String touser, MonitorConfig monitorConfig) {
        pushMessageProxy.pushMessage(message, touser, monitorConfig);
    }
}
