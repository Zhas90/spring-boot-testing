package kz.zhassulan.springboottesting.service;

import kz.zhassulan.springboottesting.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
}
