package com.knowit.lectures.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "quizzes")
public class Quiz extends BaseEntity {

    private String name;

    private String description;

    private Boolean solved;

    private String answer;

    private Long reward;

    private String category;

    private String hint;

    public Quiz() {
        this.solved = false;
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
    public Boolean getSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }

    @Column(nullable = false)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Column(nullable = false)
    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }

    @Column(nullable = false)
    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    @Column(nullable = false)
    public String getHint() { return hint; }

    public void setHint(String hint) { this.hint = hint; }
}
