package com.emerson.job_search_tool.dtos;

import java.util.UUID;

public record SkillOutputDto(UUID id, String name, SkillCategoryDto category) {    
}
