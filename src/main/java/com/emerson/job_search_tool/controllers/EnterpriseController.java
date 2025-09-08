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

import com.emerson.job_search_tool.dtos.EnterpriseCreateDto;
import com.emerson.job_search_tool.dtos.EnterpriseOutputDto;
import com.emerson.job_search_tool.services.EnterpriseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enterprises")
public class EnterpriseController {

    private final EnterpriseService enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @PostMapping
    public ResponseEntity<EnterpriseOutputDto> save(@RequestBody @Valid EnterpriseCreateDto enterpriseCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enterpriseService.save(enterpriseCreateDto));
    }

    @GetMapping
    public ResponseEntity<List<EnterpriseOutputDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(enterpriseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnterpriseOutputDto> findById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(enterpriseService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnterpriseOutputDto> update(@PathVariable UUID id, @RequestBody @Valid EnterpriseCreateDto enterpriseCreateDto) {
        return ResponseEntity.status(HttpStatus.OK).body(enterpriseService.update(id, enterpriseCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        enterpriseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
