package com.emerson.job_search_tool.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emerson.job_search_tool.models.SkillCategory;

public interface SkillCategoryRepository extends JpaRepository<SkillCategory, UUID> {
    public Optional<SkillCategory> findByName(String name);
}
