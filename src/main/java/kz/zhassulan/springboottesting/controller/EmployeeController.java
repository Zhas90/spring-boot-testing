package kz.zhassulan.springboottesting.controller;

import kz.zhassulan.springboottesting.model.Employee;
import kz.zhassulan.springboottesting.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,
                                                   @RequestBody Employee employee) {
        return employeeService.getEmployeeById(employeeId)
                .map(employeeFromDb -> {
                    employeeFromDb.setFirstName(employee.getFirstName());
                    employeeFromDb.setLastName(employee.getLastName());
                    employeeFromDb.setEmail(employee.getEmail());

                    Employee savedEmployee = employeeService.updateEmployee(employeeFromDb);
                    return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
