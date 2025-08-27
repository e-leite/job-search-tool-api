package com.emerson.job_search_tool.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

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

import jakarta.persistence.EntityNotFoundException;
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

    private ProgramingLanguageCategory getCategoryRandon() {
        ProgramingLanguageCategory[] categories = ProgramingLanguageCategory.values();
        Random rand = new Random();
        return categories[rand.nextInt(categories.length - 1)];        
    }

    @Nested
    class SaveMethod {

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

    @Nested
    class FindAllMethod {

        @Test
        public void shouldReturnAllProgramingLanguage() {
            List<ProgramingLanguage> programingLanguages = IntStream.range(1, 7)
                .mapToObj(n -> {
                    ProgramingLanguage p = new ProgramingLanguage();
                    p.setName(createRandomName());
                    p.setCategory(getCategoryRandon());
                    return programingLanguageRepository.save(p);

                }).toList();

            List<ProgramingLanguageOutputDto> result = programingLanguageService.findAll();

            Assertions.assertEquals(programingLanguages.size(), result.size());
        }
    }

    @Nested
    class FindByIdMethod {

        @Test
        public void shouldReturnProgramingLanguageSuccessfuly() {
            ProgramingLanguage p = new ProgramingLanguage();
            p.setName(createRandomName());
            p.setCategory(getCategoryRandon());
            ProgramingLanguage programingLanguage = programingLanguageRepository.save(p);

            ProgramingLanguageOutputDto result = programingLanguageService.findById(programingLanguage.getId());

            Assertions.assertNotNull(result);
        }

        @Test
        public void shouldThrowEntityNotFoundExceptionIfNotExistProgramingLanguageWithReceivedId() {
            UUID nonexistentId = UUID.randomUUID();

            Assertions.assertThrows(EntityNotFoundException.class, () -> programingLanguageService.findById(nonexistentId));
        }
    }

    @Nested
    class UpdateMethod {

        @Test
        public void shouldUpdateProgramingLanguageSuccessfuly() {            
            ProgramingLanguage p = new ProgramingLanguage();
            p.setName(createRandomName());
            p.setCategory(ProgramingLanguageCategory.BACK_END);
            ProgramingLanguage existingProgramingLanguage = programingLanguageRepository.save(p);

            ProgramingLanguageCreateDto programingLanguageCreateDto = new ProgramingLanguageCreateDto("New Name", "Front-End");

            ProgramingLanguageOutputDto result = programingLanguageService.update(existingProgramingLanguage.getId(), programingLanguageCreateDto);

            Assertions.assertEquals(result.id(), existingProgramingLanguage.getId());
            Assertions.assertEquals(result.name(), existingProgramingLanguage.getName());
            Assertions.assertEquals(result.category(), existingProgramingLanguage.getCategory().toString());
        }
    }
    
}
