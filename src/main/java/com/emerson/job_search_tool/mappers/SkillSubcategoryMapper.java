package com.emerson.job_search_tool.mappers;

import com.emerson.job_search_tool.dtos.SkillSubcategoryCreateDto;
import com.emerson.job_search_tool.dtos.SkillSubcategoryOutputDto;
import com.emerson.job_search_tool.models.SkillCategory;
import com.emerson.job_search_tool.models.SkillSubcategory;

public class SkillSubcategoryMapper {
    public static SkillSubcategory toEntity(SkillSubcategoryCreateDto skillSubcategoryCreateDto, SkillCategory skillCategory) {
        SkillSubcategory s = new SkillSubcategory();
        s.setName(skillSubcategoryCreateDto.name());
        s.setSkillCategory(skillCategory);
        return s;
    }

    public static SkillSubcategoryOutputDto toDto(SkillSubcategory skillSubcategory) {
        return new SkillSubcategoryOutputDto(
            skillSubcategory.getId(), 
            skillSubcategory.getName(), 
            skillSubcategory.getSkillCategory().getId(), 
            skillSubcategory.getCreatedAt(), 
            skillSubcategory.getUpdatedAt()
        );
    }    
}
