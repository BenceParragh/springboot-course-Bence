package hu.cubix.hr.bencepar.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String job;
	private int salary;
	private LocalDateTime startTimestamp;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	public Employee() {
	}

	public Employee(long id, String job, int salary, LocalDateTime startTimestamp) {
		this.id = id;
		this.job = job;
		this.salary = salary;
		this.startTimestamp = startTimestamp;
	}

	// Employee Bence = new Employee(1L, "Developer", 50000, LocalDateTime.of(2015,
	// 1, 1, 9, 2));

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id);
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	

}
