package hu.cubix.hr.bencepar;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.cubix.hr.bencepar.config.HrConfigurationProperties;
import hu.cubix.hr.bencepar.config.HrConfigurationProperties.Smart;
import hu.cubix.hr.bencepar.model.Employee;
import hu.cubix.hr.bencepar.service.InitDbService;
import hu.cubix.hr.bencepar.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner {

	@Autowired
	SalaryService salaryService;

	@Autowired
	HrConfigurationProperties config;

	@Autowired
	InitDbService initDbService;

	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		 Employee Bence = new Employee(1L, "Developer", 800000, LocalDateTime.of(2015, 9, 15, 20, 15, 0));
//		    salaryService.updateSalary(Bence);
//		    System.out.println("New salary: " + Bence.getSalary());
		initDbService.clearDb();
		initDbService.initDb();

		Smart smartConfig = config.getSalary().getSmart();

		for (Double limit :
//			smartConfig.getLimits().keySet()
		Arrays.asList(smartConfig.getLow(), smartConfig.getMid(), smartConfig.getHigh())) {

			int origSalary = 100;
			LocalDateTime limitDay = LocalDateTime.now().minusDays((long) (limit * 365));
			Employee e1 = new Employee(1L, "Nagy Péter", null, origSalary, limitDay.plusDays(1));
			Employee e2 = new Employee(2L, "Kis Gábor", null, origSalary, limitDay.minusDays(1));

			salaryService.setNewSalary(e1);
			salaryService.setNewSalary(e2);

			System.out.format("1 nappal a %.2f éves határ előtt az új fizetés %d%n", limit, e1.getSalary());
			System.out.format("1 nappal a %.2f éves határ után az új fizetés %d%n", limit, e2.getSalary());

		}

	}

}
