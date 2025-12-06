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

	private final EmployeeRepository employeeRepository;
	private final CompanyRepository companyRepository;

	public CompanyService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.companyRepository = companyRepository;
	}

	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public Company update(Company company) {
		if (!companyRepository.existsById(company.getCompanyId()))
			return null;
		return companyRepository.save(company);
	}

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Optional<Company> findById(long companyId) {
		return companyRepository.findById(companyId);
	}

	public void delete(long companyId) {
		companyRepository.deleteById(companyId);
	}

	public Company addEmployee(long companyId, Employee employee) {
		Company company = companyRepository.findById(companyId).get();
		company.addEmployee(employee);
		employeeRepository.save(employee);
		return company;
	}

	public Company deleteEmployee(long companyId, long id) {
		Company company = companyRepository.findById(companyId).get();
		Employee employee = employeeRepository.findById(id).get();
		employee.setCompany(null);
		company.getEmployees().remove(employee);
		employeeRepository.save(employee);
		return company;
	}

	// @Transactional
	public Company replaceEmployees(long companyId, List<Employee> employees) {
		Company company = companyRepository.findById(companyId).get();

		company.getEmployees().forEach(e -> e.setCompany(null));
		company.getEmployees().clear();

		employees.forEach(e -> {
			// company.addEmployee(employeeRepository.save(e)); //csak @Transactional
			// esetben helyes

			company.addEmployee(e);
			e.setId(employeeRepository.save(e).getId());
		});

		return company;
	}

}
