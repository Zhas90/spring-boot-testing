package kz.zhassulan.springboottesting.service;

import kz.zhassulan.springboottesting.model.Employee;
import kz.zhassulan.springboottesting.repository.EmployeeRepository;
import kz.zhassulan.springboottesting.service.impl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .id(1L)
                .firstName("Asan")
                .lastName("Usen")
                .email("asan@gmail.com")
                .build();
    }

    @Test
    @DisplayName("JUnit test for saveEmployee method")
    public void givenEmployee_whenSaveEmployee_thenReturnEmployee() {
        //given - precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.empty());

        given(employeeRepository.save(employee)).willReturn(employee);

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        //when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeService.saveEmployee(employee);

        System.out.println(savedEmployee);

        //then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
    }

}
