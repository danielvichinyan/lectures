package com.knowit.lectures.controllers;

import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.domain.models.QuizRequestModel;
import com.knowit.lectures.domain.models.QuizResponseModel;
import com.knowit.lectures.exceptions.UserDoesNotExist;
import com.knowit.lectures.services.quizzes.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/quiz")
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResponseModel addQuiz(
            @AuthenticationPrincipal User user,
            @RequestBody QuizRequestModel model
    ) throws UserDoesNotExist, IOException {
        return this.quizService.createQuiz(
                user,
                model.getName(),
                model.getDescription(),
                model.getAnswer(),
                model.getReward(),
                model.getCategory()
        );
    }

    @GetMapping("/quiz")
    public List<QuizResponseModel> getAllQuizzes() {
        return this.quizService.getAllQuizzes();
    }

    @GetMapping("/quiz/{name}")
    public QuizResponseModel getQuizByName(@PathVariable String name) {
        return this.quizService.findByName(name);
    }
}
