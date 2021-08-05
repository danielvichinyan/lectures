package com.knowit.lectures.services.quizzes;

import com.knowit.lectures.domain.entities.Lecture;
import com.knowit.lectures.domain.entities.Quiz;
import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.domain.models.LectureResponseModel;
import com.knowit.lectures.domain.models.QuizResponseModel;
import com.knowit.lectures.exceptions.UserDoesNotExist;
import com.knowit.lectures.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public QuizResponseModel createQuiz(
            User user,
            String name,
            String description,
            String answer,
            Long reward
    ) throws UserDoesNotExist, IOException {
        Quiz quiz = new Quiz();
        quiz.setName(name);
        quiz.setDescription(description);
        quiz.setAnswer(answer);
        quiz.setReward(reward);
        this.quizRepository.saveAndFlush(quiz);

        QuizResponseModel quizResponseModel = new QuizResponseModel();
        quizResponseModel.setName(quiz.getName());
        quizResponseModel.setDescription(quiz.getDescription());
        quizResponseModel.setAnswer(quiz.getAnswer());
        quizResponseModel.setReward(quiz.getReward());

        return quizResponseModel;
    }

    @Override
    public QuizResponseModel findByName(String name) {
        Quiz quiz = this.quizRepository.findByName(name);
        QuizResponseModel quizResponseModel = new QuizResponseModel();

        quizResponseModel.setName(quiz.getName());
        quizResponseModel.setDescription(quiz.getName());
        quizResponseModel.setAnswer(quiz.getAnswer());
        quizResponseModel.setReward(quiz.getReward());

        return quizResponseModel;
    }

    @Override
    public List<QuizResponseModel> getAllQuizzes() {
        List<Quiz> quizList = this.quizRepository.findAll();
        List<QuizResponseModel> mappedList = new ArrayList<>();

        quizList.forEach(quiz -> {
            QuizResponseModel quizResponseModel = new QuizResponseModel();
            quizResponseModel.setName(quiz.getName());
            quizResponseModel.setDescription(quiz.getDescription());
            quizResponseModel.setAnswer(quiz.getAnswer());
            quizResponseModel.setReward(quiz.getReward());
            mappedList.add(quizResponseModel);
        });

        return mappedList;
    }
}
