package hu.cubix.hr.bencepar.model;

import java.time.LocalDateTime;

public class Employee {

	private Long id;
	private String job;
	private int salary;
	private LocalDateTime startTimestamp;

	public Employee(long id, String job, int salary, LocalDateTime startTimestamp) {
		this.id = id;
		this.job = job;
		this.salary = salary;
		this.startTimestamp = startTimestamp;
	}
	
	//Employee Bence = new Employee(1L, "Developer", 50000, LocalDateTime.of(2015, 1, 1, 9, 2));

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

}
