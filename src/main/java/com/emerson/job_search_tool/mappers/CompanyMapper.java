package com.emerson.job_search_tool.mappers;

import com.emerson.job_search_tool.dtos.CompanyCreateDto;
import com.emerson.job_search_tool.dtos.CompanyOutputDto;
import com.emerson.job_search_tool.models.Company;

public class CompanyMapper {
    public static Company toEntity(CompanyCreateDto companyCreateDto) {
        Company company = new Company();
        company.setName(companyCreateDto.name());
        company.setOverview(companyCreateDto.overview());
        company.setSite(companyCreateDto.site());
        company.setIndustry(companyCreateDto.industry());
        company.setCompanySize(companyCreateDto.companySize());
        company.setFoundationYear(companyCreateDto.foundationYear());

        return company;
    }

    public static CompanyOutputDto toDto(Company company) {
        return new CompanyOutputDto(
            company.getId(), 
            company.getName(), 
            company.getOverview(), 
            company.getSite(), 
            company.getIndustry(), 
            company.getCompanySize(), 
            company.getFoundationYear(), 
            company.getCreatedAt(), 
            company.getUpdatedAt());
    }
}
