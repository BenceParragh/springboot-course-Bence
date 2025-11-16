package hu.cubix.hr.bencepar.service;

import org.springframework.stereotype.Service;

import hu.cubix.hr.bencepar.model.Employee;


//This class is calculation the salary based on the default or smart service.

@Service
public class SalaryService {

	private EmployeeService employeeService;

	public SalaryService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void updateSalary(Employee employee) {

		int raisePercent = employeeService.getPayRaisePercent(employee);
		int currentSalary = employee.getSalary();
		int newSalary = currentSalary + ((currentSalary * raisePercent) / 100);
		employee.setSalary(newSalary);

	}

}
