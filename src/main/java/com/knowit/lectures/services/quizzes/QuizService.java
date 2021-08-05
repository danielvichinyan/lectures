package com.knowit.lectures.services.quizzes;

import com.knowit.lectures.domain.entities.Quiz;
import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.domain.models.QuizResponseModel;
import com.knowit.lectures.exceptions.UserDoesNotExist;

import java.io.IOException;
import java.util.List;

public interface QuizService {

    QuizResponseModel createQuiz(
      User user,
      String name,
      String description,
      String answer,
      Long reward
    ) throws UserDoesNotExist, IOException;

    QuizResponseModel findByName(String name);

    List<QuizResponseModel> getAllQuizzes();
}
