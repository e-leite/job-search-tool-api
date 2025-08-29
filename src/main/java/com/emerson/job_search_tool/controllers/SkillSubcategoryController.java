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

import com.emerson.job_search_tool.dtos.SkillSubcategoryCreateDto;
import com.emerson.job_search_tool.dtos.SkillSubcategoryOutputDto;
import com.emerson.job_search_tool.services.SkillSubcategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/skillsubcategories")
public class SkillSubcategoryController {

    private final SkillSubcategoryService skillSubcategoryService;

    public SkillSubcategoryController(SkillSubcategoryService skillSubcategoryService) {
        this.skillSubcategoryService = skillSubcategoryService;
    }

    @PostMapping
    public ResponseEntity<SkillSubcategoryOutputDto> save(@RequestBody @Valid SkillSubcategoryCreateDto skillSubcategoryCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillSubcategoryService.save(skillSubcategoryCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<SkillSubcategoryOutputDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(skillSubcategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillSubcategoryOutputDto> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(skillSubcategoryService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillSubcategoryOutputDto> update(@PathVariable UUID id, @RequestBody @Valid SkillSubcategoryCreateDto skillSubcategoryCreateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(skillSubcategoryService.update(id, skillSubcategoryCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        skillSubcategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
