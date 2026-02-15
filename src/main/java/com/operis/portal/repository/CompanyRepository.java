package com.operis.portal.repository;

import com.operis.portal.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    boolean existsByIdFiscal(String idFiscal);
    boolean existsByName(String name);
}
