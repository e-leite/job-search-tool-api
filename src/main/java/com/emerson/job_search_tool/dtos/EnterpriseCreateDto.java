package com.emerson.job_search_tool.dtos;

public record EnterpriseCreateDto(
    String name, String overview, String site, String industry, String companySize, Integer foundationYear
) {
    
}
