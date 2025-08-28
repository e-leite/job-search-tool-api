package com.emerson.job_search_tool.dtos;

import jakarta.validation.constraints.NotBlank;

public record SkillCategoryCreateDto(@NotBlank(message = "Name is mandatory.") String name) {
    
}
