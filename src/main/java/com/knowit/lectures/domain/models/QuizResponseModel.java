package com.knowit.lectures.domain.models;

public class QuizResponseModel {

    private String name;

    private String description;

    private String answer;

    private Long reward;

    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) { this.reward = reward; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
