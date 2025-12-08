package hu.cubix.hr.bencepar.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import hu.cubix.hr.bencepar.model.Company;
import hu.cubix.hr.bencepar.model.Employee;
import hu.cubix.hr.bencepar.repository.CompanyRepository;
import hu.cubix.hr.bencepar.repository.EmployeeRepository;

@Service
public class InitDbService {

	private final CompanyRepository companyRepository;
	private final EmployeeRepository employeeRepository;

	public InitDbService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
		super();
		this.companyRepository = companyRepository;
		this.employeeRepository = employeeRepository;
	}

	public void clearDB() {
		companyRepository.deleteAll();
		employeeRepository.deleteAll();
	}

	public void insertTestData() {

		Company company1 = new Company();
		company1.setRegistrationNumber(4165);
		company1.setName("Sanofi");
		company1.setAddress("1138 Budapest, Fiastyúk utca 1");

		company1.getEmployees().add(
				createEmployee("Parragh Bence", "Field Specialist", 975000, LocalDateTime.of(2020, 7, 12, 15, 30, 0), company1));
		company1.getEmployees()
				.add(createEmployee("Kis Pista", "Service Engineer", 856000, LocalDateTime.of(2015, 5, 1, 10, 30, 0), company1));

		Company company2 = new Company();
		company2.setRegistrationNumber(6789);
		company2.setName("Grizzly");
		company2.setAddress("8000 Székesfehérvár, József Attila utca 35");

		company2.getEmployees()
				.add(createEmployee("Varga Ilona", "Reception", 750000, LocalDateTime.of(2010, 3, 16, 8, 15, 0), company2));
		company2.getEmployees()
				.add(createEmployee("Kiss Elemér", "Logistics", 850000, LocalDateTime.of(2024, 5, 18, 10, 30, 0), company2));

		companyRepository.save(company1);
		companyRepository.save(company2);
	}

	public Employee createEmployee(String name, String job, int salary, LocalDateTime startTimestamp, Company company) {

		Employee employee = new Employee();
		employee.setName(name);
		employee.setJob(job);
		employee.setSalary(salary);
		employee.setStartTimestamp(startTimestamp);
		employee.setCompany(company);
		return employee;
	}

}
