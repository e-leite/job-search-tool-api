package com.emerson.job_search_tool.mappers;

import com.emerson.job_search_tool.dtos.SkillCategoryCreateDto;
import com.emerson.job_search_tool.dtos.SkillCategoryOutputDto;
import com.emerson.job_search_tool.models.SkillCategory;

public class SkillCategoryMapper {
    public static SkillCategory toEntity(SkillCategoryCreateDto skillCategoryCreateDto) {
        SkillCategory s = new SkillCategory();
        s.setName(skillCategoryCreateDto.name());
        return s;
    }

    public static SkillCategoryOutputDto toDto(SkillCategory skillCategory) {
        return new SkillCategoryOutputDto(
            skillCategory.getId(), skillCategory.getName(), skillCategory.getCreatedAt(), skillCategory.getUpdatedAt()
        );
    }
    
}
