package hu.cubix.hr.bencepar.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.annotation.JsonView;

import hu.cubix.hr.bencepar.dto.CompanyDto;
import hu.cubix.hr.bencepar.dto.EmployeeDto;
import hu.cubix.hr.bencepar.dto.Views;
import hu.cubix.hr.bencepar.model.Company;
import hu.cubix.hr.bencepar.mapper.CompanyMapper;
import hu.cubix.hr.bencepar.mapper.EmployeeMapper;
import hu.cubix.hr.bencepar.service.CompanyService;
import hu.cubix.hr.bencepar.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

//	private Map<Long, CompanyDto> companies = new HashMap<>();

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private CompanyService companyService;

//	{
//		List<EmployeeDto> employees = new ArrayList<>();
//		employees.add(new EmployeeDto("Parragh Bence", 16018045, "Field Application Specialist", 850000,
//				LocalDate.of(2024, 12, 9)));
//		employees.add(new EmployeeDto("Kis Pista", 16010000, "service engineer", 750000, LocalDate.of(2020, 10, 15)));
//
//		companies.put(1357L, new CompanyDto(1357L, 2804L, "Biomerieux", "1138 Budapest", employees));
//	}

	@GetMapping
	public List<CompanyDto> getCompanies(@RequestParam Optional<Boolean> full) {
		List<Company> companies = companyService.findAll();
		return full.orElse(false) ? companyMapper.companiesToDtos(companies)
				: companyMapper.companiesToSummaryDtos(companies);
	}

	@GetMapping("/{companyId}")
	public CompanyDto findById(@PathVariable long id, @RequestParam Optional<Boolean> full) {
		Company company = companyService.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return full.orElse(false) ? companyMapper.companyToDto(company) : companyMapper.companyToSummaryDto(company);
	}

	@PostMapping
	public CompanyDto create(@RequestBody CompanyDto companyDto) {
		return companyMapper.companyToDto(companyService.save(companyMapper.dtoToCompany(companyDto)));
	}

	@PutMapping("/{companyId}")
	public CompanyDto update(@PathVariable long companyId, @RequestBody CompanyDto companyDto) {
		companyDto.setCompanyId(companyId);
		Company updatedCompany = companyService.update(companyMapper.dtoToCompany(companyDto));
		if (updatedCompany == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return companyMapper.companyToDto(updatedCompany);
	}

	@DeleteMapping("/{companyId}")
	public void delete(@PathVariable long companyId) {
		companyService.delete(companyId);
	}

	@PostMapping("/{companyId}/employees")
	public CompanyDto addNewEmployee(@PathVariable long companyId, @RequestBody EmployeeDto employeeDto) {
		Company company = companyService.addEmployee(companyId, companyMapper.dtoToEmployee(employeeDto));
		return companyMapper.companyToDto(company);
	}

	@DeleteMapping("/{companyId}/employees/{id}")
	public CompanyDto deleteEmployee(@PathVariable long companyId, @PathVariable long id) {
		Company company = companyService.deleteEmployee(companyId, id);
		return companyMapper.companyToDto(company);
	}

	@PutMapping("/{companyId}/employees")
	public CompanyDto replaceEmployees(@PathVariable long companyId, @RequestBody List<EmployeeDto> newEmployees) {
		Company company = companyService.replaceEmployees(companyId, employeeMapper.dtosToEmployees(newEmployees));
		return companyMapper.companyToDto(company);
	}

}
