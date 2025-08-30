package com.emerson.job_search_tool.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.emerson.job_search_tool.dtos.SkillCreateDto;
import com.emerson.job_search_tool.dtos.SkillOutputDto;
import com.emerson.job_search_tool.exceptions.EntityAlreadyExistsException;
import com.emerson.job_search_tool.mappers.SkillMapper;
import com.emerson.job_search_tool.models.Skill;
import com.emerson.job_search_tool.models.SkillCategory;
import com.emerson.job_search_tool.repositories.SkillCategoryRepository;
import com.emerson.job_search_tool.repositories.SkillRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final SkillCategoryRepository skillCategoryRepository;

    public SkillService(SkillRepository skillRepository, SkillCategoryRepository skillCategoryRepository) {
        this.skillRepository = skillRepository;
        this.skillCategoryRepository = skillCategoryRepository;
    }

    public SkillOutputDto save(SkillCreateDto skillCreateDto) {

        throwEntityAlreadyExistsExceptionIfExistsSkillWithReceivedName(skillCreateDto.name());

        Skill skill = new Skill();
        skill.setName(skillCreateDto.name());
        if(skillCreateDto.skillCategoryId() != null) {
            SkillCategory skillCategory = skillCategoryRepository.findById(skillCreateDto.skillCategoryId()).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id " + skillCreateDto.skillCategoryId())
            );
            skill.setCategory(skillCategory);
        }

        return SkillMapper.toDto(skillRepository.save(skill));
    }    
    
    public List<SkillOutputDto> findAll() {
        return skillRepository.findAll()
            .stream()
            .map(s -> SkillMapper.toDto(s))
            .collect(Collectors.toList());
    }

    public SkillOutputDto findById(UUID id) {
        return SkillMapper.toDto(getSkillByIdOrElseThrowEntityNotFoundException(id));
    }

    public SkillOutputDto update(UUID id, SkillCreateDto skillCreateDto) {

        this.throwEntityAlreadyExistsExceptionIfExistsSkillWithReceivedName(skillCreateDto.name());
        
        Skill skill = this.getSkillByIdOrElseThrowEntityNotFoundException(id);
        skill.setName(skillCreateDto.name());
        if(skillCreateDto.skillCategoryId() != null) {
            SkillCategory actualSkillCategory = skill.getCategory();
            SkillCategory newSkillCategory = skillCategoryRepository.findById(skillCreateDto.skillCategoryId()).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id " + skillCreateDto.skillCategoryId()));

            if(!actualSkillCategory.equals(newSkillCategory)) {
                skill.setCategory(newSkillCategory);
            }
        } else {
            skill.setCategory(null);
        }

        return SkillMapper.toDto(this.skillRepository.save(skill));
    }

    public void deleteById(UUID id) {
        Skill skill = getSkillByIdOrElseThrowEntityNotFoundException(id);
        this.skillRepository.delete(skill);
    }

    private void throwEntityAlreadyExistsExceptionIfExistsSkillWithReceivedName(String name) {
        Optional<Skill> skillO = skillRepository.findByName(name);
        if(skillO.isPresent()) {
            throw new EntityAlreadyExistsException("Already exists Skill with name " + name);
        }
    }

    private Skill getSkillByIdOrElseThrowEntityNotFoundException(UUID id) {
        return skillRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Skill not found with id " + id));
    }    
}
