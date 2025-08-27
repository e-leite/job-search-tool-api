package com.emerson.job_search_tool.enums;

import java.util.stream.Stream;

public enum ProgramingLanguageCategory {
    FRONT_END("Front-End"),
    BACK_END("Back-End");

    private String description;

    private ProgramingLanguageCategory(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public static ProgramingLanguageCategory fromDescription(String description) {
        return Stream.of(ProgramingLanguageCategory.values())
            .filter(c -> c.toString().equals(description))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Programing language category " + description + " invalid."));

    }
}
