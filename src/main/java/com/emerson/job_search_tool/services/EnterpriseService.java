package com.emerson.job_search_tool.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.emerson.job_search_tool.dtos.EnterpriseCreateDto;
import com.emerson.job_search_tool.dtos.EnterpriseOutputDto;
import com.emerson.job_search_tool.exceptions.EntityAlreadyExistsException;
import com.emerson.job_search_tool.mappers.EnterpriseMapper;
import com.emerson.job_search_tool.models.Enterprise;
import com.emerson.job_search_tool.repositories.EnterpriseRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    public EnterpriseOutputDto save(EnterpriseCreateDto enterpriseCreateDto) {
        Optional<Enterprise> enterpriseO = enterpriseRepository.findByName(enterpriseCreateDto.name());

        if(enterpriseO.isPresent()) {
            throw new EntityAlreadyExistsException("Already exists Enterprise with name" + enterpriseCreateDto.name());
        }

        Enterprise enterprise = EnterpriseMapper.toEntity(enterpriseCreateDto);

        return EnterpriseMapper.toDto(enterpriseRepository.save(enterprise));
    }

    public List<EnterpriseOutputDto> findAll() {
        return enterpriseRepository.findAll()
            .stream()
            .map(e -> EnterpriseMapper.toDto(e))
            .collect(Collectors.toList());
    }

    public EnterpriseOutputDto findById(UUID id) {
        Enterprise enterprise = getEnterpriseOrThrowEntityNotFoundException(id);

        return EnterpriseMapper.toDto(enterprise);
    }
    
    public EnterpriseOutputDto update(UUID id, EnterpriseCreateDto enterpriseCreateDto) {
        Enterprise enterprise = getEnterpriseOrThrowEntityNotFoundException(id);
        enterprise.setName(enterpriseCreateDto.name());
        enterprise.setOverview(enterpriseCreateDto.overview());
        enterprise.setSite(enterpriseCreateDto.site());
        enterprise.setIndustry(enterpriseCreateDto.industry());
        enterprise.setCompanySize(enterpriseCreateDto.companySize());
        enterprise.setFoundationYear(enterpriseCreateDto.foundationYear());
        
        return EnterpriseMapper.toDto(enterpriseRepository.save(enterprise));        
    }

    public void deleteById(UUID id) {
        Enterprise enterprise = getEnterpriseOrThrowEntityNotFoundException(id);
        enterpriseRepository.delete(enterprise);
    }    
    
    private Enterprise getEnterpriseOrThrowEntityNotFoundException(UUID id) {
        Enterprise enterprise = enterpriseRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Enterprise not found with id " + id)
        );
        return enterprise;
    }
    
}
