package com.emerson.job_search_tool.mappers;

import com.emerson.job_search_tool.dtos.ProgramingLanguageCreateDto;
import com.emerson.job_search_tool.dtos.ProgramingLanguageOutputDto;
import com.emerson.job_search_tool.enums.ProgramingLanguageCategory;
import com.emerson.job_search_tool.models.ProgramingLanguage;

public class ProgramingLanguageMapper {
    
    public static ProgramingLanguage toEntity(ProgramingLanguageCreateDto programingLanguageCreateDto) {
        ProgramingLanguage p = new ProgramingLanguage();
        p.setName(programingLanguageCreateDto.name());
        p.setCategory(ProgramingLanguageCategory.fromDescription(programingLanguageCreateDto.category()));
        return p;
    }

    public static ProgramingLanguageOutputDto toDto(ProgramingLanguage programingLanguage) {
        return new ProgramingLanguageOutputDto(
            programingLanguage.getId(), 
            programingLanguage.getName(), 
            programingLanguage.getCategory().toString(),
            programingLanguage.getCreatedAt(),
            programingLanguage.getUpdatedAt()
        );
    }
}
