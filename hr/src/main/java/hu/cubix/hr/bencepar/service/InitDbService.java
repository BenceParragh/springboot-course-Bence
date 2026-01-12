package hu.cubix.hr.bencepar.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.cubix.hr.bencepar.model.Company;
import hu.cubix.hr.bencepar.model.Employee;
import hu.cubix.hr.bencepar.model.Position;
import hu.cubix.hr.bencepar.model.PositionDetailsByCompany;
import hu.cubix.hr.bencepar.model.Qualification;
import hu.cubix.hr.bencepar.repository.CompanyRepository;
import hu.cubix.hr.bencepar.repository.EmployeeRepository;
import hu.cubix.hr.bencepar.repository.HolidayRequestRepository;
import hu.cubix.hr.bencepar.repository.PositionDetailsByCompanyRepository;
import hu.cubix.hr.bencepar.repository.PositionRepository;
import jakarta.transaction.Transactional;

@Service
public class InitDbService {

	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	
	@Autowired
	HolidayRequestRepository holidayRequestRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public void clearDb() {
		positionDetailsByCompanyRepository.deleteAllInBatch();
		holidayRequestRepository.deleteAllInBatch();
		employeeRepository.deleteAllInBatch();
		positionRepository.deleteAllInBatch();
		companyRepository.deleteAllInBatch();
	}
	
	@Transactional
	public void initDb() {
		
		Position developer = positionRepository.save(new Position("fejlesztő", Qualification.UNIVERSITY));
		Position tester = positionRepository.save(new Position("tesztelő", Qualification.HIGH_SCHOOL));
		
		Employee newEmployee1 = employeeRepository.save(new Employee(1601, "ssdf", developer, 200000, LocalDateTime.now()));
		newEmployee1.setUsername("user1");
		newEmployee1.setPassword(passwordEncoder.encode("pass"));
		
		Employee newEmployee2 = employeeRepository.save(new Employee(8045, "t35", tester, 200000, LocalDateTime.now()));
		newEmployee2.setUsername("user2");
		newEmployee2.setPassword(passwordEncoder.encode("pass"));
		newEmployee1.setManager(newEmployee2);

		Company newCompany = companyRepository.save(new Company(null, 10, "sdfsd", "", null));
		newCompany.addEmployee(newEmployee2);
		newCompany.addEmployee(newEmployee1);
		
		PositionDetailsByCompany pd = new PositionDetailsByCompany();
		pd.setCompany(newCompany);
		pd.setMinSalary(250000);
		pd.setPosition(developer);
		positionDetailsByCompanyRepository.save(pd);
		
		PositionDetailsByCompany pd2 = new PositionDetailsByCompany();
		pd2.setCompany(newCompany);
		pd2.setMinSalary(200000);
		pd2.setPosition(tester);
		positionDetailsByCompanyRepository.save(pd2);
	}

}
