package com.emerson.job_search_tool.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emerson.job_search_tool.dtos.SkillCreateDto;
import com.emerson.job_search_tool.dtos.SkillOutputDto;
import com.emerson.job_search_tool.models.Skill;
import com.emerson.job_search_tool.models.SkillCategory;
import com.emerson.job_search_tool.repositories.SkillCategoryRepository;
import com.emerson.job_search_tool.repositories.SkillRepository;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class SkillServiceIntegragionTest {

    @Autowired
    SkillCategoryRepository skillCategoryRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    SkillService skillService;

    @Nested
    class UpdateMethod {
        
        @Test
        public void shouldUpdateSkillIfSkillCategoryIdDifferent() {
            SkillCategory skillCategory = new SkillCategory();
            skillCategory.setName("Old Category");
            skillCategoryRepository.save(skillCategory);

            Skill skill = new Skill();
            skill.setName("Existent Skill");
            skill.setCategory(skillCategory);
            skillRepository.save(skill);

            SkillCreateDto skillCreateDto = new SkillCreateDto("New Skill Name", null);
            SkillOutputDto result = skillService.update(skill.getId(), skillCreateDto);


            assertEquals("New Skill Name", result.name());
            assertNull(result.category());

        }
    }
    
}
