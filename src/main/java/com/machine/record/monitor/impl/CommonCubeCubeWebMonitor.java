package com.machine.record.monitor.impl;

import com.machine.record.dao.MonitorToUserDao;
import com.machine.record.entity.CubeWebServiceConfig;
import com.machine.record.entity.MonitorConfig;
import com.machine.record.monitor.PushMessageProxy;
import com.machine.record.monitor.inter.Monitor;
import com.machine.record.util.DateUtil;
import com.machine.record.util.WebServiceUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("commonCubeCubeWebMonitor")
public class CommonCubeCubeWebMonitor implements Monitor {
    @Autowired
    PushMessageProxy pushMessageProxy;

    @Autowired
    WebServiceUtil webServiceUtil;

    @Resource
    MonitorToUserDao monitorToUserDao;

    @Override
    public String getTouser(String project) {
        return monitorToUserDao.getTouser(project);
    }

    @Override
    public String statistics(MonitorConfig monitorConfig) {
        if(monitorConfig.getMessage().contains("连接模型服务器失败")){
            return monitorConfig.getMessage();
        }
        CubeWebServiceConfig cubeWebServiceConfig = monitorConfig.getCubeWebServiceConfig();
        if (cubeWebServiceConfig == null) {
            return null;
        }
        String mdx = "";
        String dataBase = monitorConfig.getDatabase();
        String topic = monitorConfig.getTopic();
        String cubeName = monitorConfig.getCubeName();
        String dim = monitorConfig.getDim();
        String yesterday = DateUtil.getPastDate(1).replace("-", "");
        if (StringUtils.hasText(dataBase) && StringUtils.hasText(topic) && StringUtils.hasText(cubeName) && StringUtils.hasText(dim)) {
            mdx = "select [Measures].[" + cubeName + "] on 0 from [" + topic + "] where " + dim + ".&[" + yesterday + "]";
            String json = webServiceUtil.executeMdx(mdx, monitorConfig);
            if (!StringUtils.hasText(json)) {
                return monitorConfig.getMessage() + ",未查询出昨天的数据";
            }
            List<Map<String, String>> result = new Gson().fromJson(json, List.class);
            String countstr = result.get(0).get(monitorConfig.getCubeName());
            if (!StringUtils.hasText(countstr)) {
                return monitorConfig.getMessage() + ",未查询出昨天的数据";
            }
            Double count = Double.valueOf(result.get(0).get(monitorConfig.getCubeName()));


            //获取估值和阈值
            Map<String, Double> map = getCommonThresholdForCube(monitorConfig);
            Double old_value = map.get("old_value");
            Double diff_allow = map.get("diff_allow");
            Double diff = Math.abs(old_value - count);
            if (diff > diff_allow) {
                return monitorConfig.getMessage() + ":昨天指标值为" + count + ",预设值为" + old_value + ",差距为" + diff;
            }


        }
        return null;
    }

    @Override
    public void push(String message, String touser, MonitorConfig monitorConfig) {
        pushMessageProxy.pushMessage(message, touser, monitorConfig);
    }
}
