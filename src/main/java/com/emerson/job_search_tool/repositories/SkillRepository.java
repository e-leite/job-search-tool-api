package com.emerson.job_search_tool.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emerson.job_search_tool.models.Skill;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
    public Optional<Skill> findByName(String name);    
}
