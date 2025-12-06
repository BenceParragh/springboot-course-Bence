package hu.cubix.hr.bencepar.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

public class CompanyDto {

	@JsonView(Views.BaseData.class)
	private Long companyId;
	@JsonView(Views.BaseData.class)
	private int registrationNumber;
	@JsonView(Views.BaseData.class)
	private String name;
	@JsonView(Views.BaseData.class)
	private String address;

	private List<EmployeeDto> employees = new ArrayList<>();

	public CompanyDto() {

	}

	public CompanyDto(Long companyId, int registrationNumber, String name, String address, List<EmployeeDto> employees) {
		this.companyId = companyId;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.address = address;
		this.employees = employees;

	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public int getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(int registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<EmployeeDto> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDto> employees) {
		this.employees = employees;
	}

}
