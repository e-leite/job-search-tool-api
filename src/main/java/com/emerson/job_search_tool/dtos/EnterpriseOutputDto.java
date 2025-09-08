package com.emerson.job_search_tool.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record EnterpriseOutputDto(
    UUID id,
    String name,
    String overview,
    String site,
    String industry,
    String companySize,
    Integer foundationYear,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    
}
