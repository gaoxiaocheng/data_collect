package com.machine.record.quartz;

import com.machine.record.service.MonitorConfigService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class CommonUrlQuartz extends QuartzJobBean {
    @Autowired
    MonitorConfigService monitorConfigService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        monitorConfigService.monitor("", "commonurl");
    }
}
