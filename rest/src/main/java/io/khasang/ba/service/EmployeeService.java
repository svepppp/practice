package io.khasang.ba.service;

import io.khasang.ba.dto.EmployeeDto;
import io.khasang.ba.entity.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * method for add employee
     *
     * @param employee = employee for adding
     * @return created employee
     */
    Employee addEmployee(Employee employee);

    /**
     * method for getting employee by specific id
     *
     * @param id - employee's id
     * @return employee by id
     */
    EmployeeDto getEmployeeDtoById(long id);

    /**
     * method gor getting all employees
     *
     * @return all employees
     */
    List<Employee> getAllEmployees();

    /**
     * method for update employee
     *
     * @param employee - employee's with updated params
     * @return updated employee
     */
    Employee updateEmployee(Employee employee);

    /**
     * method for delete employee by id
     *
     * @param id - employee's id for delete
     * @return deleted employee
     */
    Employee deleteEmployee(long id);
}
