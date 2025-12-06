package hu.cubix.hr.bencepar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cubix.hr.bencepar.config.HrConfigurationProperties;
import hu.cubix.hr.bencepar.model.Employee;

@Service
public class DefaultEmployeeService extends AbstractEmployeeService {

	@Autowired
	HrConfigurationProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return config.getSalary().getDef().getPercent();
	}

}
