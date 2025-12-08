package hu.cubix.hr.bencepar.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.cubix.hr.bencepar.dto.AverageSalaryDto;
import hu.cubix.hr.bencepar.dto.CompanyDto;
import hu.cubix.hr.bencepar.dto.EmployeeDto;
import hu.cubix.hr.bencepar.dto.HighSalaryCompanyDto;
import hu.cubix.hr.bencepar.model.Company;
import hu.cubix.hr.bencepar.model.Employee;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	List<CompanyDto> companiesToDtos(List<Company> companies);

	CompanyDto companyToDto(Company company);

	@IterableMapping(qualifiedByName = "summary")
	List<CompanyDto> companiesToSummaryDtos(List<Company> companies);

	@Mapping(target = "employees", ignore = true)
	@Named("summary")
	CompanyDto companyToSummaryDto(Company company);

	Company dtoToCompany(CompanyDto companyDto);

	List<Company> dtosToCompanies(List<CompanyDto> companies);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "job", target = "job")
	@Mapping(source = "startTimestamp", target = "startTimestamp")
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);

	HighSalaryCompanyDto toHighSalaryDto(Company company);

	default AverageSalaryDto toAverageSalaryDto(Object[] row) {
		return new AverageSalaryDto((String) row[0], (double) row[1]);
	}

}
