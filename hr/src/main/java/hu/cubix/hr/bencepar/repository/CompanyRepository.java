package hu.cubix.hr.bencepar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hu.cubix.hr.bencepar.model.Company;
import jakarta.persistence.NamedQuery;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("SELECT DISTINCT c FROM Company c JOIN c.employees e WHERE e.salary > :salaryLimit")
	List<Company> findCompaniesWithHighSalaryEmployee(@Param("salaryLimit") long salaryLimit);

	@Query("SELECT c FROM Company c WHERE SIZE(c.employees) > :employeeLimit")
	List<Company> findCompaniesWithHighEmployeeCount(@Param("employeeLimit") int employeeLimit);

	@Query("SELECT e.job, AVG(e.salary) FROM Company c JOIN c.employees e "
			+ "WHERE c.id = :companyId GROUP BY e.job ORDER BY AVG(e.salary) DESC")
	List<Object[]> findAverageSalaryByJob(@Param("companyId") long companyId);

	//@Query("SELECT DISTINCT c FROM Company c LEFT JOIN FETCH c.employees")
	@Query("SELECT c FROM Company c")
	//@EntityGraph(attributePaths = {"employees"})
	@EntityGraph("Company.withEmployees")
	public List<Company> findAllWithEmployees();
	
	@Query("SELECT c FROM Company c WHERE c.id=:id")
	@EntityGraph("Company.withEmployees")
	public Company findByIdWithEmployees(Long id);
}
