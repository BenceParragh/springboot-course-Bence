package hu.cubix.hr.bencepar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.hr.bencepar.model.PositionDetailsByCompany;

public interface PositionDetailsByCompanyRepository extends JpaRepository<PositionDetailsByCompany, Long> {

	List<PositionDetailsByCompany> findByPositionNameAndCompanyCompanyId(String positionName, long companyId);

}