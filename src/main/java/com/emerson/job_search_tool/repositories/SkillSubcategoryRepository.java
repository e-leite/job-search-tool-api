package com.emerson.job_search_tool.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emerson.job_search_tool.models.SkillSubcategory;

public interface SkillSubcategoryRepository extends JpaRepository<SkillSubcategory, UUID> {
    public Optional<SkillSubcategory> findByName(String name);    
}
