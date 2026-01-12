package hu.cubix.hr.bencepar.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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
	
	@OneToMany(mappedBy = "employee")
	private List<HolidayRequest> holidayRequests;

	@ManyToOne
	private Position position;

	@ManyToOne
	private Employee manager;
	
	private String username;
	private String password;

	public Employee() {
	}

	public Employee(long id, String name, Position position, int salary, LocalDateTime startTimestamp) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startTimestamp = startTimestamp;
	}

	public Employee(int salary, LocalDateTime startTimestamp) {
		super();
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
	
	public List<HolidayRequest> getHolidayRequests() {
		return holidayRequests;
	}

	public void setHolidayRequests(List<HolidayRequest> holidayRequests) {
		this.holidayRequests = holidayRequests;
	}
	
	public void addHolidayRequest(HolidayRequest holidayRequest) {
		if (this.holidayRequests == null)
			this.holidayRequests = new ArrayList<>();

		this.holidayRequests.add(holidayRequest);
		holidayRequest.setEmployee(this);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
