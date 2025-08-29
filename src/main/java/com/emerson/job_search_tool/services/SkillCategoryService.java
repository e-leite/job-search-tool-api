package com.emerson.job_search_tool.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emerson.job_search_tool.dtos.SkillCategoryCreateDto;
import com.emerson.job_search_tool.exceptions.EntityAlreadyExistsException;
import com.emerson.job_search_tool.models.SkillCategory;
import com.emerson.job_search_tool.repositories.SkillCategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SkillCategoryService {

    private final SkillCategoryRepository skillCategoryRepository;

    public SkillCategoryService(SkillCategoryRepository skillCategoryRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
    }

    public SkillCategory save(SkillCategoryCreateDto skillCategoryCreateDto) {

        throwEntityAlreadyExistsExceptionIfExistsSkillCategoryWithReceivedName(skillCategoryCreateDto.name());
        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setName(skillCategoryCreateDto.name());

        return skillCategoryRepository.save(skillCategory);
    }    
    
    public List<SkillCategory> findAll() {
        return skillCategoryRepository.findAll();
    }

    public SkillCategory findById(UUID id) {
        return getSkillCategoryByIdOrElseThrowEntityNotFoundException(id);
    }

    public SkillCategory update(UUID id, SkillCategoryCreateDto skillCategoryCreateDto) {

        this.throwEntityAlreadyExistsExceptionIfExistsSkillCategoryWithReceivedName(skillCategoryCreateDto.name());
        
        SkillCategory skillCategory = this.getSkillCategoryByIdOrElseThrowEntityNotFoundException(id);
        skillCategory.setName(skillCategoryCreateDto.name());
        return this.skillCategoryRepository.save(skillCategory);
    }

    public void deleteById(UUID id) {
        SkillCategory skillCategory = getSkillCategoryByIdOrElseThrowEntityNotFoundException(id);
        this.skillCategoryRepository.delete(skillCategory);
    }

    private void throwEntityAlreadyExistsExceptionIfExistsSkillCategoryWithReceivedName(String name) {
        Optional<SkillCategory> skillCategoryO = skillCategoryRepository.findByName(name);
        if(skillCategoryO.isPresent()) {
            throw new EntityAlreadyExistsException("Already exists Skill Category with name " + name);
        }
    }

    private SkillCategory getSkillCategoryByIdOrElseThrowEntityNotFoundException(UUID id) {
        return skillCategoryRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Skill Category not found with id " + id));
    }
}
