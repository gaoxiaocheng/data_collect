package com.machine.record.monitor.impl;

import com.machine.record.dao.MonitorToUserDao;
import com.machine.record.entity.MonitorConfig;
import com.machine.record.monitor.PushMessageProxy;
import com.machine.record.monitor.inter.Monitor;
import com.machine.record.util.DateUtil;
import com.machine.record.util.HiveDBUtil;
import com.machine.record.util.MysqlDBUtil;
import com.machine.record.util.SqlServerDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service("wechatRealTimeMonitor")
public class WechatRealTimeMonitor implements Monitor {
    @Autowired
    PushMessageProxy pushMessageProxy;

    @Autowired
    MonitorToUserDao monitorToUserDao;

    @Override
    public String getTouser(String project) {
        return monitorToUserDao.getTouser(project);
    }


    @Override
    public String statistics(MonitorConfig monitorConfig) {

        //todo 查看时间点
        String date = DateUtil.getPastDate(7);
        String hour = DateUtil.date2String(null, "HH");

        //TODO 查询现在的值
        Double nowValue = getValueForCommonSql(monitorConfig);
        //todo 查询一周前的数
        Double oldValue = getOldValue(monitorConfig, date, hour);
        if (oldValue == -1.0) {
            //无历史数据，不判断
            return monitorConfig.getMessage() + "未查出历史指标值";
        }
        //todo 查看是否周末
        Calendar calendar = Calendar.getInstance();
        Integer week = calendar.get(Calendar.DAY_OF_WEEK);
        Double diff_allow;
        if (week == 1 || week == 7) {
            //周末
            diff_allow = monitorConfig.getHolyDayThresholdValue();
        } else {
            diff_allow = monitorConfig.getWorkDayThresholdValue();
        }
        //todo 判断是否超过阈值范围
        Double diff = Math.abs(oldValue - nowValue);
        if (diff > diff_allow) {
            if(oldValue != null){
                Double prcent = (double) Math.round((diff * 100) / oldValue);
                return monitorConfig.getMessage() + ":目前指标值为" + nowValue + ",周同期值为" + oldValue + ",差值为" + diff + "，差值比例为 " + prcent + "%";
            }
        }

        //if(超过阈值范围){
        //   return  monitorConfig.getMessage();
        // }
        return null;
    }

    @Override
    public void push(String message, String touser, MonitorConfig monitorConfig) {
        pushMessageProxy.pushMessage(message, touser, monitorConfig);
    }

    private Double getOldValue(MonitorConfig monitorConfig, String date, String hour) {
        String dataBase = monitorConfig.getDatabase();
        String userName = monitorConfig.getDataUser();
        String password = monitorConfig.getDataPassword();
        String type = monitorConfig.getType();
        String sql = "select * from sub_hisory_data_save where sub_code = '" + monitorConfig.getStatCode() + "'" +
                " and h_date = '" + date + "' and h_hour = '" + hour + "'";
        List<Map<String, Object>> data = new ArrayList<>();
        try {
            if ("sqlserver".equals(type)) {
                data = SqlServerDBUtil.findResult(sql, new ArrayList<>(), dataBase, userName, password);
            } else if ("mysql".equals(type)) {
                data = MysqlDBUtil.findResult(sql, new ArrayList<>(), dataBase, userName, password);
            } else if ("hive".equals(type)) {
                data = HiveDBUtil.findResult(sql, new ArrayList<>(), dataBase, userName, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (data != null && data.size() > 0) {
            return Double.valueOf(data.get(0).get("h_value").toString());
        }
        return -1.0;
    }

}
