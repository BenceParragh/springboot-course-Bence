package hu.cubix.hr.bencepar.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cubix.hr.bencepar.config.HrConfigurationProperties;
import hu.cubix.hr.bencepar.config.HrConfigurationProperties.Raise.Smart.Years;
import hu.cubix.hr.bencepar.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService {

	@Autowired
	private HrConfigurationProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {

		LocalDateTime start = employee.getStartTimestamp();

		Years yearsConfig = config.getRaise().getSmart().getYears();

		double yearsWorked = Duration.between(start, LocalDateTime.now()).toDays() / 365.0;
		System.out.println(yearsWorked);

		if (yearsWorked < yearsConfig.getLow()) {
			return yearsConfig.getLowPercent();
		} else if (yearsWorked < yearsConfig.getMid()) {
			return yearsConfig.getMidLowPercent();
		} else if (yearsWorked < yearsConfig.getHigh()) {
			return yearsConfig.getHighMidPercent();
		} else if (yearsWorked >= yearsConfig.getHigh()) {
			return yearsConfig.getHighPercent();
		} else {
			return yearsConfig.getLowPercent();
		}

	}

}
