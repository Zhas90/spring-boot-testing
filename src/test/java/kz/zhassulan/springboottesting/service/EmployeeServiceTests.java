package kz.zhassulan.springboottesting.service;

import kz.zhassulan.springboottesting.model.Employee;
import kz.zhassulan.springboottesting.repository.EmployeeRepository;
import kz.zhassulan.springboottesting.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Optional;

public class EmployeeServiceTests {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    @DisplayName("JUnit test for saveEmployee method")
    public void givenEmployee_whenSaveEmployee_thenReturnEmployee() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .id(1L)
                .firstName("Asan")
                .lastName("Usen")
                .email("asan@gmail.com")
                .build();
        BDDMockito.given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

        BDDMockito.given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        //when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeService.saveEmployee(employee);

        System.out.println(savedEmployee);

        //then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }

}
