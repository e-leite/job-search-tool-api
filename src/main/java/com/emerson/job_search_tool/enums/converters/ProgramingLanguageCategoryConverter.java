package com.emerson.job_search_tool.enums.converters;

import com.emerson.job_search_tool.enums.ProgramingLanguageCategory;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProgramingLanguageCategoryConverter implements AttributeConverter<ProgramingLanguageCategory, String> {

    @Override
    public String convertToDatabaseColumn(ProgramingLanguageCategory programingLanguageCategory) {
        if(programingLanguageCategory == null) {
            return null;
        }
        return programingLanguageCategory.toString();
    }

    @Override
    public ProgramingLanguageCategory convertToEntityAttribute(String categoryName) {
        if(categoryName == null) {
            return null;
        }

        return ProgramingLanguageCategory.fromDescription(categoryName);
    }
    
}
