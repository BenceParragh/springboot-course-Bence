package hu.cubix.hr.bencepar;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.cubix.hr.bencepar.model.Employee;
import hu.cubix.hr.bencepar.service.EmployeeService;
import hu.cubix.hr.bencepar.service.SalaryService;
import hu.cubix.hr.bencepar.service.SmartEmployeeService;


@SpringBootApplication
public class HrApplication implements CommandLineRunner{

	@Autowired
	SalaryService salaryService;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
		 Employee Bence = new Employee(1L, "Developer", 800000, LocalDateTime.of(2023, 5, 17, 9, 2));
		    salaryService.updateSalary(Bence);
		    System.out.println("New salary: " + Bence.getSalary());
	}

}
