package com.emerson.job_search_tool.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emerson.job_search_tool.models.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, UUID> {
    
    public Optional<Enterprise> findByName(String name);
    
}
