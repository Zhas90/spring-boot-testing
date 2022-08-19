package kz.zhassulan.springboottesting.repository;

import kz.zhassulan.springboottesting.model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Asan")
                .lastName("Asan")
                .email("asan@gmail.com")
                .build();

        //when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);

    }

    // JUnit test for get all employees operation
    @Test
    @DisplayName("JUnit test for get all employees operation")
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Asan")
                .lastName("Asan")
                .email("asan@gmail.com")
                .build();

        Employee employee1 = Employee.builder()
                .firstName("Jalgas")
                .lastName("Nurlan")
                .email("nurlan@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        //when - action or the behaviour that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    // JUnit test for get employee by id operation
    @Test
    @DisplayName("JUnit test for get employee by id operation")
    public void givenEmployee_whenFindById_thenReturnEmployee() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Asan")
                .lastName("Asan")
                .email("asan@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();

    }

    // JUnit test for get employee by email operation
    @Test
    @DisplayName("JUnit test for get employee by email operation")
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployee() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Inju")
                .lastName("Baimanova")
                .email("inju@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or the behaviour that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();
        assertThat(employeeDB.getId()).isEqualTo(employee.getId());
    }

    // JUnit test for update employee operation
    @Test
    @DisplayName("JUnit test for update employee operation")
    public void givenEmployee_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Inju")
                .lastName("Baimanova")
                .email("inju@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or the behaviour that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("baimanova@gmail.com");

        //then - verify the output
        assertThat(employee.getEmail()).isEqualTo("baimanova@gmail.com");
    }

}
