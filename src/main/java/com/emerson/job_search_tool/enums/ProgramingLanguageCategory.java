package com.emerson.job_search_tool.enums;

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
}
