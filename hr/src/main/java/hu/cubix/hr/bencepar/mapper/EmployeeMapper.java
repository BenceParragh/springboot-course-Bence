package hu.cubix.hr.bencepar.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.hr.bencepar.dto.EmployeeDto;
import hu.cubix.hr.bencepar.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	 
	List<EmployeeDto> employeesToDtos(List<Employee> employees);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "job", target = "job")
	@Mapping(source = "startTimestamp", target = "startTimestamp")
	@Mapping(target = "company.employees", ignore = true)
	EmployeeDto employeeToDto(Employee employee);
	
	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);
	
	List<Employee> dtosToEmployees(List<EmployeeDto> employees);
	
}
