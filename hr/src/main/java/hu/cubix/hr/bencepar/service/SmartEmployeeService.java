package hu.cubix.hr.bencepar.service;

import java.time.LocalDate;
import java.time.Period;

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

		LocalDate start = employee.getStartTimestamp();

		Years yearsConfig = config.getRaise().getSmart().getYears();

		double yearsWorked = Period.between(start, LocalDate.now()).getDays() / 365.0;

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
