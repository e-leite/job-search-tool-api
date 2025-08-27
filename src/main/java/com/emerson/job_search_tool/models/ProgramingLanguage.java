package com.emerson.job_search_tool.models;

import java.io.Serializable;
import java.util.UUID;

import com.emerson.job_search_tool.enums.ProgramingLanguageCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "programing_language")
public class ProgramingLanguage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is mandatory.")
    @Column(name = "name", unique = true, length = 30)
    private String name;    
    private ProgramingLanguageCategory category;

    public ProgramingLanguage() {
    }

    public ProgramingLanguage(UUID id, @NotBlank(message = "Name is mandatory.") String name,
            ProgramingLanguageCategory category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProgramingLanguageCategory getCategory() {
        return category;
    }

    public void setCategory(ProgramingLanguageCategory category) {
        this.category = category;
    }    
}
