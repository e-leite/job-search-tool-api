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

import com.emerson.job_search_tool.dtos.ProgramingLanguageCreateDto;
import com.emerson.job_search_tool.dtos.ProgramingLanguageOutputDto;
import com.emerson.job_search_tool.services.ProgramingLanguageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/programinglanguages")
public class ProgramingLanguageController {

    private final ProgramingLanguageService programingLanguageService;

    public ProgramingLanguageController(ProgramingLanguageService programingLanguageService) {
        this.programingLanguageService = programingLanguageService;
    }

    @PostMapping
    public ResponseEntity<ProgramingLanguageOutputDto> save(@RequestBody @Valid ProgramingLanguageCreateDto programingLanguageCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(programingLanguageService.save(programingLanguageCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<ProgramingLanguageOutputDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(programingLanguageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramingLanguageOutputDto> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(programingLanguageService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramingLanguageOutputDto> update(@PathVariable UUID id, @RequestBody @Valid ProgramingLanguageCreateDto programingLanguageCreateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(programingLanguageService.update(id, programingLanguageCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        programingLanguageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
    
}
