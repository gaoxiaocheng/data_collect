package com.machine.record.dao;

import com.machine.record.common.Result;
import com.machine.record.entity.HardwareDefinition;

public interface HardwardDefinitionDao {
    Result define(HardwareDefinition hardwareDefinition);
}
