package io.khasang.ba.dto;

import io.khasang.ba.entity.Car;
import io.khasang.ba.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDto {

    private List<CarDto> carDtoList = new ArrayList<>();
    private Long id;
    private String name;
    private String description;

    public EmployeeDto getEmployeeDTO(Employee employee) {
        List<CarDto> carDtoList = new ArrayList<>();
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setId(employee.getId());
        employeeDto.setDescription(employee.getDescription());

        getCarDtoFromCar(employee, carDtoList);

        employeeDto.setCarDtoList(carDtoList);
        return employeeDto;
    }

    private void getCarDtoFromCar(Employee employee, List<CarDto> carDtoList) {
        for (Car car : employee.getCarList()) {
            CarDto carDto = new CarDto();
            carDto.setId(car.getId());
            carDto.setModel(car.getModel());
            carDto.setYear(car.getYear());

            carDtoList.add(carDto);
        }
    }

    public List<CarDto> getCarDtoList() {
        return carDtoList;
    }

    public void setCarDtoList(List<CarDto> carDtoList) {
        this.carDtoList = carDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
