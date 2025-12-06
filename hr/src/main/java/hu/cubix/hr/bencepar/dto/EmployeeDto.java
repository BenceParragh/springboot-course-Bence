package hu.cubix.hr.bencepar.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class EmployeeDto {

	private String name;
	private Long id;
	@NotEmpty
	private String job;
	@Positive
	private int salary;
	@Past
	private LocalDateTime startTimestamp;
	
	public EmployeeDto() {
		
	}

	public EmployeeDto(String name, long id, String job, int salary, LocalDateTime startTimestamp) {
		this.name = name;
		this.id = id;
		this.job = job;
		this.salary = salary;
		this.startTimestamp = startTimestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDateTime getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(LocalDateTime startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
