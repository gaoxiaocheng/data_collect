package com.machine.record.service.impl;

import com.machine.record.dao.MonitorConfigDao;
import com.machine.record.dao.MonitorToUserDao;
import com.machine.record.service.MonitorConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorConfigServiceImpl implements MonitorConfigService {
    @Autowired
    MonitorConfigDao monitorConfigDao;

    @Autowired
    MonitorToUserDao monitorToUserDao;
    @Override
    public void monitor(String project, String monitorType) {
        monitorConfigDao.monitor(project,monitorType);
    }
}
