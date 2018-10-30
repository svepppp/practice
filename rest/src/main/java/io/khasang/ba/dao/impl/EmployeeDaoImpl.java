package io.khasang.ba.dao.impl;

import io.khasang.ba.dao.EmployeeDao;
import io.khasang.ba.entity.Employee;

public class EmployeeDaoImpl extends BasicDaoImpl<Employee> implements EmployeeDao {
    public EmployeeDaoImpl(Class<Employee> entityClass) {
        super(entityClass);
    }
}
