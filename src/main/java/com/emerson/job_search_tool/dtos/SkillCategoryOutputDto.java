package com.emerson.job_search_tool.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SkillCategoryOutputDto(
    UUID id, 
    String name, 
    List<CategorySubcategoryDto> subcategory,
    LocalDateTime createdAt, 
    LocalDateTime updatedAt) {
    
}
