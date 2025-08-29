package com.emerson.job_search_tool.dtos;

import jakarta.validation.constraints.NotBlank;

public record SkillSubcategoryCreateDto(@NotBlank(message = "Name is mandatory.") String name) {
    
}
