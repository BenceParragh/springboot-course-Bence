package hu.cubix.hr.bencepar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.cubix.hr.bencepar.model.Company;
import hu.cubix.hr.bencepar.model.Employee;
import hu.cubix.hr.bencepar.repository.CompanyRepository;
import hu.cubix.hr.bencepar.repository.EmployeeRepository;
import jakarta.transaction.Transactional;

@Service
public class CompanyService {

	private final EmployeeService employeeService;
	private final CompanyRepository companyRepository;
	
	public CompanyService(EmployeeService employeeService, CompanyRepository companyRepository) {
		super();
		this.employeeService = employeeService;
		this.companyRepository = companyRepository;
	}

	@Transactional
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	@Transactional
	public Company update(Company company) {
		if (!companyRepository.existsById(company.getCompanyId()))
			return null;
		return companyRepository.save(company);
	}

	public List<Company> findAll(boolean full) {
		return full ? companyRepository.findAllWithEmployees() : companyRepository.findAll();
	}

	public Optional<Company> findById(long companyId, boolean full) {
		return full ? companyRepository.findByIdWithEmployees(companyId) : companyRepository.findById(companyId);
	}

	public void delete(long companyId) {
		companyRepository.deleteById(companyId);
	}

	@Transactional
	public Company addEmployee(long companyId, Employee employee) {
		Company company = companyRepository.findByIdWithEmployees(companyId).get();
		company.addEmployee(employeeService.save(employee));
		return company;
	}

	@Transactional
	public Company deleteEmployee(long companyId, long id) {
		Company company = companyRepository.findById(companyId).get();
		Employee employee = employeeService.findById(id).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeService.save(employee);
		return company;
	}

	@Transactional
	public Company replaceEmployees(long companyId, List<Employee> employees) {
		Company company = companyRepository.findById(companyId).get();

		company.getEmployees().forEach(e -> e.setCompany(null));
		company.getEmployees().clear();

		employees.forEach(e -> {
			company.addEmployee(employeeService.save(e)); //csak @Transactional
			// esetben helyes

//			company.addEmployee(e);
//			e.setId(employeeService.save(e).getId());
		});

		return company;
	}

}
