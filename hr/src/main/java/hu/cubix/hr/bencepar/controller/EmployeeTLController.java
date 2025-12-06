package hu.cubix.hr.bencepar.controller;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import hu.cubix.hr.bencepar.dto.EmployeeDto;

@Controller
public class EmployeeTLController {

	private List<EmployeeDto> employees = new ArrayList<>();

//	{
//		employees.add(new EmployeeDto("Parragh Bence", 16018045, "Field Application Specialist", 800000,
//				LocalDate.of(2024, 12, 9)));
//	}

	@GetMapping("/employees")
	public String home(Map<String, Object> model) {
		model.put("employees", employees);
		model.put("newEmployee", new EmployeeDto());
		return "employees";
	}

	@PostMapping("/employees")
	public String createEmployee(EmployeeDto employee) {
		employees.add(employee);
		return "redirect:/employees";
	}

	@GetMapping("/employees/edit/{id}")
	public String editPage(@PathVariable long id, Model model) {
		EmployeeDto employee = employees.stream().filter(emp -> emp.getId() == id).findFirst().orElse(null);
		model.addAttribute("employee", employee);
		return "edit_employee";
	}

	@PostMapping("/employees/update/{id}")
	public String updateEmployee(@PathVariable long id, @ModelAttribute EmployeeDto employee) {
		employees.removeIf(emp -> emp.getId() == id);
		employees.add(employee);
		return "redirect:/employees";
	}

	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable long id) {
		employees.removeIf(emp -> emp.getId() == id);
		return "redirect:/employees";
	}
}
