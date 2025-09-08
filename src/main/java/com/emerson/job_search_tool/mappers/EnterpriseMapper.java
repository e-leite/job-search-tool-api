package com.emerson.job_search_tool.mappers;

import com.emerson.job_search_tool.dtos.EnterpriseCreateDto;
import com.emerson.job_search_tool.dtos.EnterpriseOutputDto;
import com.emerson.job_search_tool.models.Enterprise;

public class EnterpriseMapper {
    public static Enterprise toEntity(EnterpriseCreateDto enterpriseCreateDto) {
        Enterprise enterprise = new Enterprise();
        enterprise.setName(enterpriseCreateDto.name());
        enterprise.setOverview(enterpriseCreateDto.overview());
        enterprise.setSite(enterpriseCreateDto.site());
        enterprise.setIndustry(enterpriseCreateDto.industry());
        enterprise.setCompanySize(enterpriseCreateDto.companySize());
        enterprise.setFoundationYear(enterpriseCreateDto.foundationYear());

        return enterprise;
    }

    public static EnterpriseOutputDto toDto(Enterprise enterprise) {
        return new EnterpriseOutputDto(
            enterprise.getId(), 
            enterprise.getName(), 
            enterprise.getOverview(), 
            enterprise.getSite(), 
            enterprise.getIndustry(), 
            enterprise.getCompanySize(), 
            enterprise.getFoundationYear(), 
            enterprise.getCreatedAt(), 
            enterprise.getUpdatedAt());
    }
}
