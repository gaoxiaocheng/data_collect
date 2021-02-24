package com.machine.record.dao.impl;

import com.machine.record.common.Result;
import com.machine.record.dao.HardwardDefinitionDao;
import com.machine.record.entity.HardwareDefinition;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class HardwardDefinitionDaoImpl implements HardwardDefinitionDao {

    @PersistenceContext(unitName = "entityManageFactoryPrimary")
    private EntityManager entityManager;

    @Override
    public Result define(HardwareDefinition hardwareDefinition) {
        //todo 判断code是否为空，如果为空自动生成code
        String code = hardwareDefinition.getCode();
        if(StringUtils.isEmpty(code)){
            code = String.valueOf(System.currentTimeMillis());
        }
        hardwareDefinition.setCode(code);
        entityManager.persist(hardwareDefinition);

        final Result result = new Result(code);
        return result;
    }
}
