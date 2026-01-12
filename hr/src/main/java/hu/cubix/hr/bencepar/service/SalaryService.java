package hu.cubix.hr.bencepar.service;

import org.springframework.stereotype.Service;

import hu.cubix.hr.bencepar.model.Employee;
import hu.cubix.hr.bencepar.repository.EmployeeRepository;
import hu.cubix.hr.bencepar.repository.PositionDetailsByCompanyRepository;
import hu.cubix.hr.bencepar.repository.PositionRepository;
import jakarta.transaction.Transactional;


//This class is calculation the salary based on the default or smart service.

@Service
public class SalaryService {

	private EmployeeService employeeService;
	private PositionRepository positionRepository;
	private PositionDetailsByCompanyRepository positionDetailsByCompanyRepository;
	private EmployeeRepository employeeRepository;

	public SalaryService(EmployeeService employeeService, PositionRepository positionRepository,
			PositionDetailsByCompanyRepository positionDetailsByCompanyRepository,
			EmployeeRepository employeeRepository) {
		super();
		this.employeeService = employeeService;
		this.positionRepository = positionRepository;
		this.positionDetailsByCompanyRepository = positionDetailsByCompanyRepository;
		this.employeeRepository = employeeRepository;
	}

	public void setNewSalary(Employee employee) {
		int newSalary = employee.getSalary() * (100 + employeeService.getPayRaisePercent(employee)) / 100;
		employee.setSalary(newSalary);
	}
	
	@Transactional
	public void raiseMinimalSalary(long companyId, String positionName, int minimalSalary) {
		positionDetailsByCompanyRepository.findByPositionNameAndCompanyCompanyId(positionName, companyId)
		.forEach(pd -> {
			pd.setMinSalary(minimalSalary);
			//1. megoldás: nem hatékony, mert az érintett employee-kra egyesével UPDATE queryk futnak
//			pd.getCompany().getEmployees().forEach( e -> {
//				if(e.getPosition().getName().equals(positionName)
//						&& e.getSalary() < minimalSalary) {
//					e.setSalary(minimalSalary);
//				}
//			});
			employeeRepository.updateSalaries(positionName, minimalSalary, companyId);
		});
	}
	
}