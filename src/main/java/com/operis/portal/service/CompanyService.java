package com.operis.portal.service;

import com.operis.portal.dto.CompanyDTO;
import com.operis.portal.model.Company;
import com.operis.portal.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public CompanyDTO getCompanyById(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        return mapToDTO(company);
    }

    public CompanyDTO createCompany(CompanyDTO dto) {
        if (companyRepository.existsByName(dto.getName()) || companyRepository.existsByIdFiscal(dto.getIdFiscal())) {
            throw new RuntimeException("Company already exists");
        }

        Company company = Company.builder()
                .name(dto.getName())
                .idFiscal(dto.getIdFiscal())
                .active(true)
                .build();

        companyRepository.save(company);
        return mapToDTO(company);
    }

    public CompanyDTO updateCompany(UUID id, CompanyDTO dto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setName(dto.getName());
        company.setIdFiscal(dto.getIdFiscal());
        company.setActive(dto.isActive());

        companyRepository.save(company);
        return mapToDTO(company);
    }

    public void deleteCompany(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setActive(false); // soft delete
        companyRepository.save(company);
    }

    private CompanyDTO mapToDTO(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .idFiscal(company.getIdFiscal())
                .active(company.isActive())
                .build();
    }
}
