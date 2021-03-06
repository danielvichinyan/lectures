package com.knowit.lectures.domain.models;

public class LectureRequestModel {

    private String name;

    private String description;

    private String category;

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
