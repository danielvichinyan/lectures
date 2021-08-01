package com.knowit.lectures.services.lectures;

import com.knowit.lectures.domain.entities.Lecture;
import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.domain.models.LectureResponseModel;
import com.knowit.lectures.exceptions.UserDoesNotExist;

import java.io.IOException;
import java.util.List;

public interface LecturesService {

    LectureResponseModel createLecture(
            User user,
            String name,
            String description,
            String category
    ) throws UserDoesNotExist, IOException;

    Lecture findById(String id);

    List<LectureResponseModel> getAllLectures();
}
