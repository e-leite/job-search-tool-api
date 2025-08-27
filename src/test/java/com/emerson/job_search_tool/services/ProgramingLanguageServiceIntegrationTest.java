package com.emerson.job_search_tool.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emerson.job_search_tool.dtos.ProgramingLanguageCreateDto;
import com.emerson.job_search_tool.dtos.ProgramingLanguageOutputDto;
import com.emerson.job_search_tool.enums.ProgramingLanguageCategory;
import com.emerson.job_search_tool.exceptions.EntityAlreadyExistsException;
import com.emerson.job_search_tool.models.ProgramingLanguage;
import com.emerson.job_search_tool.repositories.ProgramingLanguageRepository;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class ProgramingLanguageServiceIntegrationTest {

    @Autowired
    ProgramingLanguageRepository programingLanguageRepository;

    @Autowired
    ProgramingLanguageService programingLanguageService;

    private String createRandomName() {
        return UUID.randomUUID().toString().substring(12);
    }

    @Nested
    class saveMethod {

        @Test
        public void shouldCreateProgramingLanguageSuccessfuly() {

            ProgramingLanguageCreateDto dto = new ProgramingLanguageCreateDto(createRandomName(), "Front-End");

            ProgramingLanguageOutputDto p = programingLanguageService.save(dto);

            assertNotNull(p);            
        }

        @Test
        public void shouldReturnInvalidArgumetExceptionIfCategoryIsInvalid() {
            
            ProgramingLanguage p = new ProgramingLanguage();
            p.setName(createRandomName());
            p.setCategory(ProgramingLanguageCategory.BACK_END);
            ProgramingLanguage existingProgramingLanguage = programingLanguageRepository.save(p);
            
            ProgramingLanguageCreateDto dto = new ProgramingLanguageCreateDto(existingProgramingLanguage.getName(), "Front-End");            

            Assertions.assertThrows(EntityAlreadyExistsException.class, () -> programingLanguageService.save(dto));
        }        
    }
    
}
