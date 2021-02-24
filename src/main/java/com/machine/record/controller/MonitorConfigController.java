package com.machine.record.controller;

import com.machine.record.service.MonitorConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("monitor")
@RestController
@Api(value = "monitor", tags = "数据监控报警")
public class MonitorConfigController {

    @Autowired
    MonitorConfigService monitorConfigService;


    @ApiOperation("手工监控入口")
    @RequestMapping(value = "monitor", method = RequestMethod.GET)
    public String monitor(String project, String monitorType) {
        monitorConfigService.monitor(project, monitorType);
        return "监控完成";
    }


}
