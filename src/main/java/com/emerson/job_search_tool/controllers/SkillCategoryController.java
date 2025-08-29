package com.emerson.job_search_tool.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emerson.job_search_tool.dtos.SkillCategoryCreateDto;
import com.emerson.job_search_tool.models.SkillCategory;
import com.emerson.job_search_tool.services.SkillCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/skillcategories")
public class SkillCategoryController {

    private final SkillCategoryService skillCategoryService;

    public SkillCategoryController(SkillCategoryService skillCategoryService) {
        this.skillCategoryService = skillCategoryService;
    }

    @PostMapping
    public ResponseEntity<SkillCategory> save(@RequestBody @Valid SkillCategoryCreateDto skillCategoryCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillCategoryService.save(skillCategoryCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<SkillCategory>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(skillCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillCategory> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(skillCategoryService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillCategory> update(@PathVariable UUID id, @RequestBody @Valid SkillCategoryCreateDto skillCategoryCreateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(skillCategoryService.update(id, skillCategoryCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        skillCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }    
}
