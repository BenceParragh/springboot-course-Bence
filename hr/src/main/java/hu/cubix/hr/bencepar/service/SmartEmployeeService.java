package hu.cubix.hr.bencepar.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cubix.hr.bencepar.config.HrConfigurationProperties;
import hu.cubix.hr.bencepar.config.HrConfigurationProperties.Smart;
import hu.cubix.hr.bencepar.model.Employee;

@Service
public class SmartEmployeeService extends AbstractEmployeeService {

	@Autowired
	HrConfigurationProperties config;

	@Override
	public int getPayRaisePercent(Employee employee) {

		double yearsWorked = ChronoUnit.DAYS.between(employee.getStartTimestamp(), LocalDateTime.now()) / 365.0;
		Smart smartConfig = config.getSalary().getSmart();

		if (yearsWorked > smartConfig.getHigh())
			return smartConfig.getHighPercent();

		if (yearsWorked > smartConfig.getMid())
			return smartConfig.getMidPercent();

		if (yearsWorked > smartConfig.getLow()) {
			return smartConfig.getLowPercent();
		} else {
			return smartConfig.getLowPercent();
		}
			

	}
}

// opcionális feladat
//		TreeMap<Double, Integer> limitsMap = smartConfig.getLimits();
// 1. megoldás

//		Integer maxLimit = null;
//		for(var entry: limitsMap.entrySet()) {
//			if(yearsWorked > entry.getKey()) {
//				maxLimit = entry.getValue();
//			} else {
//				break;
//			}
//		}
//		
//		return maxLimit ==  null ? 0 : maxLimit;

// 2. megoldás
//		Optional<Double> optionalMax = limitsMap.keySet()
//			.stream()
//			.filter(k -> yearsWorked > k)
//			.max(Double::compare);
//		
//		return optionalMax.isEmpty() ? 0 : limitsMap.get(optionalMax.get());

// 3. megoldás
//		Entry<Double, Integer> floorEntry = limitsMap.floorEntry(yearsWorked);
//		return floorEntry == null ? 0 : floorEntry.getValue();
//	}
