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

import com.emerson.job_search_tool.dtos.CompanyCreateDto;
import com.emerson.job_search_tool.dtos.CompanyOutputDto;
import com.emerson.job_search_tool.services.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyOutputDto> save(@RequestBody @Valid CompanyCreateDto companyCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.save(companyCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<CompanyOutputDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyOutputDto> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyOutputDto> update(@PathVariable UUID id, @RequestBody @Valid CompanyCreateDto companyCreateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.update(id, companyCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
