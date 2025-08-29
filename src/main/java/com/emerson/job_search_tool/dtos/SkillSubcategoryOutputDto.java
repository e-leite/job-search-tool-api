package com.emerson.job_search_tool.dtos;

import java.time.LocalDateTime;
import java.util.UUID;


public record SkillSubcategoryOutputDto(
    UUID id, 
    String name,
    UUID categoryId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {    
}
