package com.operis.portal.repository;

import com.operis.portal.model.UserCompany;
import com.operis.portal.model.User;
import com.operis.portal.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserCompanyRepository extends JpaRepository<UserCompany, UUID> {
    List<UserCompany> findByUser(User user);
    List<UserCompany> findByCompany(Company company);
    Optional<UserCompany> findByUserAndCompany(User user, Company company);
}
