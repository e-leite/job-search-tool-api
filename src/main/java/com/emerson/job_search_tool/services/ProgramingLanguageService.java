package com.emerson.job_search_tool.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.emerson.job_search_tool.dtos.ProgramingLanguageCreateDto;
import com.emerson.job_search_tool.dtos.ProgramingLanguageOutputDto;
import com.emerson.job_search_tool.exceptions.EntityAlreadyExistsException;
import com.emerson.job_search_tool.mappers.ProgramingLanguageMapper;
import com.emerson.job_search_tool.models.ProgramingLanguage;
import com.emerson.job_search_tool.repositories.ProgramingLanguageRepository;

@Service
public class ProgramingLanguageService {

    private final ProgramingLanguageRepository programingLanguageRepository;

    public ProgramingLanguageService(
        ProgramingLanguageRepository programingLanguageRepository
    ) {
        this.programingLanguageRepository = programingLanguageRepository;
    }

    public ProgramingLanguageOutputDto save(ProgramingLanguageCreateDto programingLanguageCreateDto) {

        Optional<ProgramingLanguage> pOptional = this.programingLanguageRepository.findByName(programingLanguageCreateDto.name());
        if(pOptional.isPresent()) {
            throw new EntityAlreadyExistsException("Already exists Programing Language " + programingLanguageCreateDto.name());
        }

        ProgramingLanguage programingLanguage = ProgramingLanguageMapper.toEntity(programingLanguageCreateDto);
        return ProgramingLanguageMapper.toDto(this.programingLanguageRepository.save(programingLanguage));
    }
    
    public List<ProgramingLanguageOutputDto> findAll() {
        return programingLanguageRepository.findAll()
            .stream()
            .map(p -> ProgramingLanguageMapper.toDto(p))
            .collect(Collectors.toList());
    }
}
