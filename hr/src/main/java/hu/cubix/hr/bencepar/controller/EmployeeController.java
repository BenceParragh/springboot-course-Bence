package hu.cubix.hr.bencepar.controller;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.cubix.hr.bencepar.dto.EmployeeDto;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private Map<Long, EmployeeDto> employees = new HashMap<>();

	{
		employees.put(16018045L,
				new EmployeeDto(16018045, "Field Application Specialist", 850000, LocalDate.of(2024, 12, 9)));
	}

	@GetMapping
	public List<EmployeeDto> findAll() {
		return new ArrayList<>(employees.values());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> findById(@PathVariable long id) {
		EmployeeDto employeeDto = employees.get(id);
		if (employeeDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(employeeDto);
	}

	@PostMapping
	public ResponseEntity<EmployeeDto> create(@RequestBody EmployeeDto employee) {
		if (employees.containsKey(employee.getId()))
			return ResponseEntity.badRequest().build();

		employees.put(employee.getId(), employee);
		return ResponseEntity.ok(employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> update(@PathVariable long id, @RequestBody EmployeeDto employee) {
		employee.setId(id);
		if (!employees.containsKey(id))
			return ResponseEntity.notFound().build();

		employees.put(id, employee);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		employees.remove(id);
	}

}
