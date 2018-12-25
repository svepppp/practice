package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.RoleDao;
import io.khasang.ba.entity.Role;

public class RoleDaoImpl extends BasicDaoImpl<Role> implements RoleDao {
    public RoleDaoImpl(Class<Role> entityClass) {
        super(entityClass);
    }
}
