package com.emerson.job_search_tool.dtos;

import java.util.UUID;

public record SkillCreateDto(
    String name,
    UUID skillCategoryId
) {
    
}
