package kz.zhassulan.springboottesting.repository;

import kz.zhassulan.springboottesting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
