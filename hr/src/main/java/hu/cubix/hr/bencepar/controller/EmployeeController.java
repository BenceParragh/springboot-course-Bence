package hu.cubix.hr.bencepar.controller;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import hu.cubix.hr.bencepar.service.EmployeeService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.cubix.hr.bencepar.dto.EmployeeDto;
import hu.cubix.hr.bencepar.mapper.EmployeeMapper;
import hu.cubix.hr.bencepar.model.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
    private EmployeeService employeeService;
    
    @Autowired
	private EmployeeMapper employeeMapper;

//	private Map<Long, EmployeeDto> employees = new HashMap<>();

//	{
//		employees.put(16018045L,
//				new EmployeeDto("Parragh Bence", 16018045, "Field Application Specialist", 850000, LocalDate.of(2024, 12, 9)));
//	}

	@GetMapping
	public List<EmployeeDto> getEmployees(@RequestParam Optional<Integer> minSalary, @SortDefault("id") Pageable pageable){
		if(minSalary.isEmpty())
			return employeeMapper.employeesToDtos(employeeService.findAll(pageable).getContent());
		
		Page<Employee> page = employeeService.findBySalaryGreaterThan(minSalary.get(), pageable);
		System.out.println(page.getTotalElements());
		System.out.println(page.getTotalPages());
		System.out.println(page.isFirst());
		System.out.println(page.isLast());
		return employeeMapper.employeesToDtos(page.getContent());  
	}

	
	@GetMapping("/{id}")
	public EmployeeDto findById(@PathVariable long id) {
		Employee employee = findByIdOrThrow(id);
		return employeeMapper.employeeToDto(employee);
	}
	private Employee findByIdOrThrow(long id) {
		return employeeService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping
	public EmployeeDto create(@RequestBody @Valid EmployeeDto employeeDto) {
		return employeeMapper.employeeToDto(employeeService.save(employeeMapper.dtoToEmployee(employeeDto)));
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDto> update(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		employeeDto.setId(id);
		Employee updatedEmployee = employeeService.update(employeeMapper.dtoToEmployee(employeeDto));
		if (updatedEmployee == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(employeeMapper.employeeToDto(updatedEmployee));
		}
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		employeeService.delete(id);
	}
	
	@PutMapping("/payRaise")
	public int getPayRaisePercent(@RequestBody Employee employee) {
		return employeeService.getPayRaisePercent(employee);
	}
	
	@PostMapping("/search")
	public List<EmployeeDto> findByExample(@RequestBody EmployeeDto example) {
		return employeeMapper.employeesToDtos(employeeService.findEmployeesByExample(employeeMapper.dtoToEmployee(example)));
	}
	

}
