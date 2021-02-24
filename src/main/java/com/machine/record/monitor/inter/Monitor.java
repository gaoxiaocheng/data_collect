package com.machine.record.monitor.inter;

import com.machine.record.entity.MonitorConfig;
import com.machine.record.util.HiveDBUtil;
import com.machine.record.util.MysqlDBUtil;
import com.machine.record.util.OracleDBUtil;
import com.machine.record.util.SqlServerDBUtil;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.*;

public interface Monitor {
    default void sendMessage(MonitorConfig monitorConfig) {
        String message = statistics(monitorConfig);
        if (StringUtils.hasText(message)) {
//            String project = "wechat";
            String project = monitorConfig.getProject();
            String touser = getTouser(project);
            push(message, touser, monitorConfig);
        }

    }

    default Double getValueForCommonSql(MonitorConfig monitorConfig) {
        String dataBase = monitorConfig.getDatabase();
        String userName = monitorConfig.getDataUser();
        String password = monitorConfig.getDataPassword();
        String type = monitorConfig.getType().toLowerCase();
        String sql = monitorConfig.getMonitorHql();
        List<Map<String, Object>> data = new ArrayList<>();
        try {
            if ("sqlserver".equals(type)) {
                data = SqlServerDBUtil.findResult(sql, new ArrayList<>(), dataBase, userName, password);
            } else if ("mysql".equals(type)) {
                data = MysqlDBUtil.findResult(sql, new ArrayList<>(), dataBase, userName, password);
            } else if ("hive".equals(type)) {
                data = HiveDBUtil.findResult(sql, new ArrayList<>(), dataBase, userName, password);
            }else if("oracle".equals(type)){
                data = OracleDBUtil.findResult(sql,new ArrayList<>(),dataBase, userName, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (data != null && data.size() > 0) {
            Map<String, Object> map = data.get(0);
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            return Double.valueOf(iterator.next().getValue().toString());
        }
        return 0.0;
    }

    default Map<String, Double> getCommonThresholdForsql(MonitorConfig monitorConfig) {
        Calendar calendar = Calendar.getInstance();
        Integer week = calendar.get(Calendar.DAY_OF_WEEK);
        Double old_value, diff_allow;
        if (week == 1 || week == 7) {
            //周末
            old_value = monitorConfig.getHolyDayValue();
            diff_allow = monitorConfig.getHolyDayThresholdValue();
        } else {
            old_value = monitorConfig.getWorkDayValue();
            diff_allow = monitorConfig.getWorkDayThresholdValue();
        }
        Map<String, Double> result = new HashMap<>();
        result.put("old_value", old_value);
        result.put("diff_allow", diff_allow);
        return result;
    }

    default Map<String, Double> getCommonThresholdForCube(MonitorConfig monitorConfig) {
        Calendar calendar = Calendar.getInstance();
        Integer week = calendar.get(Calendar.DAY_OF_WEEK);
        Double old_value, diff_allow;
        if (week == 1 || week == 2) {
            //周末
            old_value = monitorConfig.getHolyDayValue();
            diff_allow = monitorConfig.getHolyDayThresholdValue();
        } else {
            old_value = monitorConfig.getWorkDayValue();
            diff_allow = monitorConfig.getWorkDayThresholdValue();
        }
        Map<String, Double> result = new HashMap<>();
        result.put("old_value", old_value);
        result.put("diff_allow", diff_allow);
        return result;
    }

    String getTouser(String project);

    public String statistics(MonitorConfig monitorConfig);

    void push(String message, String touser, MonitorConfig monitorConfig);
}
