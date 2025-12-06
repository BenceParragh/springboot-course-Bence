package hu.cubix.hr.bencepar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.hr.bencepar.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findBySalaryGreaterThan(Integer minSalary);

	
}
