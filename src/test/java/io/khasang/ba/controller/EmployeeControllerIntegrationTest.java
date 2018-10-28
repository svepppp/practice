package io.khasang.ba.controller;

import io.khasang.ba.dto.EmployeeDto;
import io.khasang.ba.entity.Car;
import io.khasang.ba.entity.Employee;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeControllerIntegrationTest {
    private static final String ROOT = "http://localhost:8080/employee";
    private static final String ADD = "/add";
    private static final String GET_BY_ID = "/get";
    private static final String ALL = "/all";

    @Test
    public void addEmployee() {
        Employee employee = createdEmployee();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + "/{id}",
                HttpMethod.GET,
                null,
                EmployeeDto.class,
                employee.getId()
        );

        assertEquals("OK", responseEntity.getStatusCode().getReasonPhrase());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void checkGetAllEmployees() {
        createdEmployee();
        createdEmployee();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(
                ROOT + GET_BY_ID + ALL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                }
        );

        List<Employee> employees = responseEntity.getBody();
        assertNotNull(employees.get(0));
        assertNotNull(employees.get(1));
    }

    private Employee createdEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        Employee employee = prefillEmployee();
        HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);
        RestTemplate restTemplate = new RestTemplate();
        Employee createdEmployee = restTemplate.exchange(
                ROOT + ADD,
                HttpMethod.POST,
                entity,
                Employee.class
        ).getBody();

        assertNotNull(createdEmployee);
        assertNotNull(createdEmployee.getName());
        return createdEmployee;
    }

    private Employee prefillEmployee() {
        Employee employee = new Employee();
        employee.setName("Jack");
        employee.setDescription("Hero of the year!");

        Car car1 = new Car();
        car1.setModel("LADA");
        car1.setYear(LocalDate.of(1992, 11, 12));
        Car car2 = new Car();
        car2.setModel("BMW");
        car2.setYear(LocalDate.of(2017, 3, 5));

        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        employee.setCarList(carList);

        return employee;
    }
}
