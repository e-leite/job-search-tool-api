package com.emerson.job_search_tool.enums.converters;

import java.util.stream.Stream;

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
        return Stream.of(ProgramingLanguageCategory.values())
            .filter(c -> c.toString().equals(categoryName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Programing language category " + categoryName + " invalid."));            
    }
    
}
