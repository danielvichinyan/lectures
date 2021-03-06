package com.knowit.lectures.domain.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lectures")
public class Lecture extends BaseEntity {

    private String name;

    private String description;

    private String category;

    private boolean isVideoFinished;

    public Lecture() {
        this.isVideoFinished = false;
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public boolean isVideoFinished() {
        return isVideoFinished;
    }

    public void setVideoFinished(boolean videoFinished) {
        isVideoFinished = videoFinished;
    }

    @Column(nullable = false)
    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
