package com.emerson.job_search_tool.mappers;

import com.emerson.job_search_tool.dtos.SkillSubcategoryCreateDto;
import com.emerson.job_search_tool.dtos.SkillSubcategoryOutputDto;
import com.emerson.job_search_tool.models.SkillSubcategory;

public class SkillSubcategoryMapper {
    public static SkillSubcategory toEntity(SkillSubcategoryCreateDto skillSubcategoryCreateDto) {
        SkillSubcategory s = new SkillSubcategory();
        s.setName(skillSubcategoryCreateDto.name());
        return s;
    }

    public static SkillSubcategoryOutputDto toDto(SkillSubcategory skillSubcategory) {
        return new SkillSubcategoryOutputDto(
            skillSubcategory.getId(), skillSubcategory.getName(), skillSubcategory.getCreatedAt(), skillSubcategory.getUpdatedAt()
        );
    }    
}
