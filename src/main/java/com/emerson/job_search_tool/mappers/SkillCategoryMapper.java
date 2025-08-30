package com.emerson.job_search_tool.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.emerson.job_search_tool.dtos.CategorySubcategoryDto;
import com.emerson.job_search_tool.dtos.SkillCategoryDto;
import com.emerson.job_search_tool.dtos.SkillCategoryOutputDto;
import com.emerson.job_search_tool.models.SkillCategory;

public class SkillCategoryMapper {
    public static SkillCategory toEntity(SkillCategoryDto skillCategoryDto) {
        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setName(skillCategoryDto.name());
        return skillCategory;
    }

    public static SkillCategoryOutputDto toDto(SkillCategory skillCategory) {
        List<CategorySubcategoryDto> subcategory = skillCategory.getSubcategory()
                .stream()
                .map(s -> new CategorySubcategoryDto(s.getId(), s.getName()))
                .collect(Collectors.toList());
        
        return new SkillCategoryOutputDto(
            skillCategory.getId(), 
            skillCategory.getName(),
            subcategory,
            skillCategory.getCreatedAt(), 
            skillCategory.getUpdatedAt());
    }
}
