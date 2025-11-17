package hu.cubix.hr.bencepar.controller;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.cubix.hr.bencepar.dto.EmployeeDto;

@Controller
public class EmployeeTLController {

	private List<EmployeeDto> employees = new ArrayList<>();
	
	{
		employees.add(new EmployeeDto(16018045, "Field Application Specialist", 800000, LocalDate.of(2024, 12, 9)));
	}

	@GetMapping("/employeeservice")
	public String home(Map<String, Object> model) {
		model.put("employees", employees);
		model.put("newEmployee", new EmployeeDto());
		return "index";
	}
	
	@PostMapping("/employeeservice")
	public String createEmployee(EmployeeDto employee) {
		employees.add(employee);
		return "redirect:/employeeservice";
	}
}
