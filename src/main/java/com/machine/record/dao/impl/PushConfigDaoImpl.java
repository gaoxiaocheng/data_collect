package com.machine.record.dao.impl;

import com.machine.record.dao.PushConfigDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PushConfigDaoImpl implements PushConfigDao {
    @PersistenceContext(unitName = "entityManageFactoryPrimary")
    private EntityManager entityManager;

}
