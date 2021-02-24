package com.machine.record.dao.impl;

import com.machine.record.dao.MonitorToUserDao;
import com.machine.record.entity.MonitorToUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MonitorToUserDaoImpl implements MonitorToUserDao {
    @PersistenceContext(unitName = "entityManageFactoryPrimary")
    private EntityManager entityManager;

    @Override
    public String getTouser(String project) {
        String sql = "select * from m_touser  where project = '" + project + "' and status = '1'";
        Query query = entityManager.createNativeQuery(sql, MonitorToUser.class);
        List<MonitorToUser> list = query.getResultList();
        if (list != null && list.size() > 0) {
            return list.get(0).getTouser();
        }
        return "";
    }
}
