package com.emerson.job_search_tool.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emerson.job_search_tool.models.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    
    public Optional<Company> findByName(String name);
    
}
