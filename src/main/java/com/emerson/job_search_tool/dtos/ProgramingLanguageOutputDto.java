package com.emerson.job_search_tool.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProgramingLanguageOutputDto(
    UUID id,
    String name,
    String category,
    LocalDateTime createdAt,
    LocalDateTime updatedAt    
) {}
