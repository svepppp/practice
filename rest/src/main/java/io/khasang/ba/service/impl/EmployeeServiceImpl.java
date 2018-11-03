package io.khasang.ba.service.impl;

import io.khasang.ba.dao.EmployeeDao;
import io.khasang.ba.dto.EmployeeDto;
import io.khasang.ba.entity.Employee;
import io.khasang.ba.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeDto employeeDto;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeDao.add(employee);
    }

    @Override
    public EmployeeDto getEmployeeDtoById(long id) {
        return employeeDto.getEmployeeDTO(employeeDao.getById(id));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public Employee deleteEmployee(long id) {
        return employeeDao.delete(employeeDao.getById(id));
    }
}
