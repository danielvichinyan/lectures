package com.knowit.lectures.controllers;

import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.domain.models.LectureRequestModel;
import com.knowit.lectures.domain.models.LectureResponseModel;
import com.knowit.lectures.exceptions.UserDoesNotExist;
import com.knowit.lectures.services.lectures.LecturesService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class LectureController {

    private final LecturesService lecturesService;

    public LectureController(LecturesService lecturesService) {
        this.lecturesService = lecturesService;
    }

    @PostMapping("/lecture")
    @ResponseStatus(HttpStatus.CREATED)
    public LectureResponseModel addLecture(
            @AuthenticationPrincipal User user,
            @RequestBody LectureRequestModel model
    ) throws UserDoesNotExist, IOException {
        return this.lecturesService.createLecture(
                user,
                model.getName(),
                model.getDescription(),
                model.getCategory()
        );
    }

    @GetMapping("/lecture")
    public List<LectureResponseModel> getAllLectures() {
        return this.lecturesService.getAllLectures();
    }
}
