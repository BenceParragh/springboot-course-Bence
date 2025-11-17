package hu.cubix.hr.bencepar.dto;

import java.time.LocalDate;

public class EmployeeDto {

	private Long id;
	private String job;
	private int salary;
	private LocalDate startTimestamp;
	
	public EmployeeDto() {
		
	}

	public EmployeeDto(long id, String job, int salary, LocalDate startTimestamp) {
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

	public LocalDate getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(LocalDate startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

}
