package hu.cubix.hr.bencepar.service;

import org.springframework.stereotype.Service;

import hu.cubix.hr.bencepar.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService {

	@Override
	public int getPayRaisePercent(Employee employee) {
		return 5;
	}

}
