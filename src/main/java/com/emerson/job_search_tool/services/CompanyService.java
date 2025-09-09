package com.emerson.job_search_tool.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.emerson.job_search_tool.dtos.CompanyCreateDto;
import com.emerson.job_search_tool.dtos.CompanyOutputDto;
import com.emerson.job_search_tool.exceptions.EntityAlreadyExistsException;
import com.emerson.job_search_tool.mappers.CompanyMapper;
import com.emerson.job_search_tool.models.Company;
import com.emerson.job_search_tool.repositories.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyOutputDto save(CompanyCreateDto companyCreateDto) {
        Optional<Company> companyO = companyRepository.findByName(companyCreateDto.name());

        if(companyO.isPresent()) {
            throw new EntityAlreadyExistsException("Already exists Company with name" + companyCreateDto.name());
        }

        Company company = CompanyMapper.toEntity(companyCreateDto);

        return CompanyMapper.toDto(companyRepository.save(company));
    }

    public List<CompanyOutputDto> findAll() {
        return companyRepository.findAll()
            .stream()
            .map(e -> CompanyMapper.toDto(e))
            .collect(Collectors.toList());
    }

    public CompanyOutputDto findById(UUID id) {
        Company company = getCompanyOrThrowEntityNotFoundException(id);

        return CompanyMapper.toDto(company);
    }
    
    public CompanyOutputDto update(UUID id, CompanyCreateDto companyCreateDto) {
        Company company = getCompanyOrThrowEntityNotFoundException(id);
        company.setName(companyCreateDto.name());
        company.setOverview(companyCreateDto.overview());
        company.setSite(companyCreateDto.site());
        company.setIndustry(companyCreateDto.industry());
        company.setCompanySize(companyCreateDto.companySize());
        company.setFoundationYear(companyCreateDto.foundationYear());
        
        return CompanyMapper.toDto(companyRepository.save(company));        
    }

    public void deleteById(UUID id) {
        Company company = getCompanyOrThrowEntityNotFoundException(id);
        companyRepository.delete(company);
    }    
    
    private Company getCompanyOrThrowEntityNotFoundException(UUID id) {
        Company company = companyRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Company not found with id " + id)
        );
        return company;
    }
    
}
