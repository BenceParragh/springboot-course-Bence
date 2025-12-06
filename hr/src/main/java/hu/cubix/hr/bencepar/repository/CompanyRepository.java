package hu.cubix.hr.bencepar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.hr.bencepar.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
