package hu.cubix.hr.bencepar;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.cubix.hr.bencepar.model.Employee;
import hu.cubix.hr.bencepar.service.InitDbService;
import hu.cubix.hr.bencepar.service.SalaryService;


@SpringBootApplication
public class HrApplication implements CommandLineRunner{

	@Autowired
	SalaryService salaryService;
	
	@Autowired
	InitDbService initDbService;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
//		 Employee Bence = new Employee(1L, "Developer", 800000, LocalDateTime.of(2015, 9, 15, 20, 15, 0));
//		    salaryService.updateSalary(Bence);
//		    System.out.println("New salary: " + Bence.getSalary());
		    initDbService.clearDB();
		    initDbService.insertTestData();
	}

}
