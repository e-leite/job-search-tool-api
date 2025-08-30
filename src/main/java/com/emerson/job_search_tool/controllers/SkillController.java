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

import com.emerson.job_search_tool.dtos.SkillCreateDto;
import com.emerson.job_search_tool.dtos.SkillOutputDto;
import com.emerson.job_search_tool.services.SkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping
    public ResponseEntity<SkillOutputDto> save(@RequestBody @Valid SkillCreateDto skillCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(skillService.save(skillCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<SkillOutputDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillOutputDto> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillOutputDto> update(@PathVariable UUID id, @RequestBody @Valid SkillCreateDto skillCreateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.update(id, skillCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        skillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }    
}
