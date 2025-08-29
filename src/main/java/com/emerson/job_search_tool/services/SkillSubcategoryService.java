package com.emerson.job_search_tool.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.emerson.job_search_tool.dtos.SkillSubcategoryCreateDto;
import com.emerson.job_search_tool.dtos.SkillSubcategoryOutputDto;
import com.emerson.job_search_tool.exceptions.EntityAlreadyExistsException;
import com.emerson.job_search_tool.mappers.SkillSubcategoryMapper;
import com.emerson.job_search_tool.models.SkillCategory;
import com.emerson.job_search_tool.models.SkillSubcategory;
import com.emerson.job_search_tool.repositories.SkillCategoryRepository;
import com.emerson.job_search_tool.repositories.SkillSubcategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SkillSubcategoryService {

    private final SkillSubcategoryRepository skillSubcategoryRepository;
    private final SkillCategoryRepository skillCategoryRepository;

    public SkillSubcategoryService(
        SkillSubcategoryRepository skillSubcategoryRepository,
        SkillCategoryRepository skillCategoryRepository) {
        this.skillSubcategoryRepository = skillSubcategoryRepository;
        this.skillCategoryRepository = skillCategoryRepository;
    }

    public SkillSubcategoryOutputDto save(SkillSubcategoryCreateDto skillSubcategoryCreateDto) {

        SkillCategory skillCategory = skillCategoryRepository.findById(skillSubcategoryCreateDto.categoryId())
            .orElseThrow(() -> new EntityNotFoundException("Skill Category not found with id " + skillSubcategoryCreateDto.categoryId()));

        throwEntityAlreadyExistsExceptionIfExistsSkillCategoryWithReceivedName(skillSubcategoryCreateDto.name());

        SkillSubcategory skillSubcategory = skillSubcategoryRepository.save(SkillSubcategoryMapper.toEntity(skillSubcategoryCreateDto, skillCategory));

        return SkillSubcategoryMapper.toDto(skillSubcategory);
    }    
    
    public List<SkillSubcategoryOutputDto> findAll() {
        return skillSubcategoryRepository.findAll()
            .stream()
            .map(sc -> SkillSubcategoryMapper.toDto(sc))
            .toList();
    }

    public SkillSubcategoryOutputDto findById(UUID id) {
        SkillSubcategory skillSubcategory = getSkillSubcategoryByIdOrElseThrowEntityNotFoundException(id);
        return SkillSubcategoryMapper.toDto(skillSubcategory);
    }

    public SkillSubcategoryOutputDto update(UUID id, SkillSubcategoryCreateDto skillSubcategoryCreateDto) {

        this.throwEntityAlreadyExistsExceptionIfExistsSkillCategoryWithReceivedName(skillSubcategoryCreateDto.name());
        
        SkillSubcategory skillSubcategory = this.getSkillSubcategoryByIdOrElseThrowEntityNotFoundException(id);
        skillSubcategory.setName(skillSubcategoryCreateDto.name());
        return SkillSubcategoryMapper.toDto(this.skillSubcategoryRepository.save(skillSubcategory));
    }

    public void deleteById(UUID id) {
        SkillSubcategory skillSubcategory = getSkillSubcategoryByIdOrElseThrowEntityNotFoundException(id);
        this.skillSubcategoryRepository.delete(skillSubcategory);
    }

    private void throwEntityAlreadyExistsExceptionIfExistsSkillCategoryWithReceivedName(String name) {
        Optional<SkillSubcategory> skillCategoryO = skillSubcategoryRepository.findByName(name);
        if(skillCategoryO.isPresent()) {
            throw new EntityAlreadyExistsException("Already exists Skill Category with name " + name);
        }
    }

    private SkillSubcategory getSkillSubcategoryByIdOrElseThrowEntityNotFoundException(UUID id) {
        return skillSubcategoryRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Skill Category not found with id " + id));
    }    
}
