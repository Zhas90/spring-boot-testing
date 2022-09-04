package kz.zhassulan.springboottesting.service;

import kz.zhassulan.springboottesting.exception.ResourceNotFoundException;
import kz.zhassulan.springboottesting.model.Employee;
import kz.zhassulan.springboottesting.repository.EmployeeRepository;
import kz.zhassulan.springboottesting.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
        assertThat(savedEmployee).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for saveEmployee method which throws exception")
    public void givenExistingEmail_whenSaveEmployee_thenThrowsException() {
        // given - precondition or setup
        given(employeeRepository.findByEmail(employee.getEmail()))
                .willReturn(Optional.of(employee));

        System.out.println(employeeRepository);
        System.out.println(employeeService);

        // when - action or the behaviour that we are going to test
        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> employeeService.saveEmployee(employee));

        // then
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    @DisplayName("JUnit test for getEmployees method")
    public void givenEmployeesList_whenGetAllEmployees_thenEmployeesList() {
        // given - precondition or setup
        Employee employee1 = Employee.builder()
                .id(2L)
                .firstName("Nurlan")
                .lastName("Beast")
                .email("nurlan@gmail.com")
                .build();
        given(employeeRepository.findAll()).willReturn(List.of(employee, employee1));

        // when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("JUnit test for getEmployees method (negative scenario)")
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenEmptyEmployeesList() {
        // given - precondition or setup
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        // when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeService.getAllEmployees();

        // then
        assertThat(employeeList).isEmpty();
    }

    @Test
    @DisplayName("JUnit test for getEmployeeById method")
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployee() {
        //given - precondition or setup
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employee));

        //when - action or the behaviour that we are going to test
        Employee fetchedEmployee = employeeService.getEmployeeById(employee.getId()).get();

        //then - verify the output
        assertThat(fetchedEmployee).isNotNull();
    }

}
