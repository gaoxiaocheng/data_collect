package com.machine.record.controller;

import com.machine.record.common.Result;
import com.machine.record.entity.HardwareDefinition;
import com.machine.record.service.HardwardDefinitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("hardward")
@RestController
@Api(value = "hardward", tags = "硬件注册地址")
public class HardwardDefinitionController {

    @Autowired
    HardwardDefinitionService hardwardDefinitionService;

    @ApiOperation("硬件定义入口")
    @RequestMapping(value = "define", method = RequestMethod.POST)
    private Result define(HardwareDefinition hardwareDefinition){
        Result result = hardwardDefinitionService.define(hardwareDefinition);
        return result;
    }
}
