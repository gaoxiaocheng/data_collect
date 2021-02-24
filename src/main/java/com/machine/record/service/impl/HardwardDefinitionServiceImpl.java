package com.machine.record.service.impl;

import com.machine.record.common.Result;
import com.machine.record.dao.HardwardDefinitionDao;
import com.machine.record.entity.HardwareDefinition;
import com.machine.record.service.HardwardDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HardwardDefinitionServiceImpl implements HardwardDefinitionService {

    @Autowired
    HardwardDefinitionDao hardwardDefinitionDao;


    @Override
    public Result define(HardwareDefinition hardwareDefinition) {

        return hardwardDefinitionDao.define(hardwareDefinition);
    }
}
