package com.emerson.job_search_tool.mappers;

import com.emerson.job_search_tool.dtos.SkillCategoryDto;
import com.emerson.job_search_tool.dtos.SkillCreateDto;
import com.emerson.job_search_tool.dtos.SkillOutputDto;
import com.emerson.job_search_tool.models.Skill;
import com.emerson.job_search_tool.models.SkillCategory;

public class SkillMapper {
    public static Skill toEntity(SkillCreateDto skillCreateDto, SkillCategory skillCategory) {
        Skill skill = new Skill();
        skill.setName(skillCreateDto.name());
        skill.setCategory(skillCategory);
        return skill;
    }

    public static SkillOutputDto toDto(Skill skill) {
        SkillCategoryDto category = null;
        if(skill.getCategory() != null) {
            category = new SkillCategoryDto(skill.getCategory().getId(), skill.getCategory().getName());

        }
        return new SkillOutputDto(skill.getId(), skill.getName(), category);
    }
    
}
