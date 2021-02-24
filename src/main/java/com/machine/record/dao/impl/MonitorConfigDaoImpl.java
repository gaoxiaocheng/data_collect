package com.machine.record.dao.impl;

import com.machine.record.dao.MonitorConfigDao;
import com.machine.record.entity.MonitorConfig;
import com.machine.record.monitor.MonitorProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MonitorConfigDaoImpl implements MonitorConfigDao {
    @Autowired
    MonitorProxy monitorProxy;

    @PersistenceContext(unitName = "entityManageFactoryPrimary")
    private EntityManager entityManager;

    @Override
    public void monitor(String project, String monitorType) {
        String sql = "";
        if(StringUtils.hasText(project)){
            sql = "select * from m_config c left join m_push_config pc on pc.push_id = c.push_config_id left join m_webservice w on c.stat_code = w.code where upper(project) = upper('" + project + "') and upper(monitor_type) = upper('" + monitorType + "')";
        }else{
            sql = "select * from m_config c left join m_push_config pc on pc.push_id = c.push_config_id left join m_webservice w on c.stat_code = w.code where  upper(monitor_type) = upper('" + monitorType + "')";
        }
        Query query = entityManager.createNativeQuery(sql, MonitorConfig.class);
        List<MonitorConfig> monitorConfigs = query.getResultList();
        if (monitorConfigs != null && monitorConfigs.size() > 0) {
            for (MonitorConfig monitorConfig : monitorConfigs) {
                monitorProxy.monitor(monitorConfig);
            }
        }
    }
}
