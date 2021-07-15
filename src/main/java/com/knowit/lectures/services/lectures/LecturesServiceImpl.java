package com.knowit.lectures.services.lectures;

import com.knowit.lectures.domain.entities.Lecture;
import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.domain.models.LectureResponseModel;
import com.knowit.lectures.exceptions.UserDoesNotExist;
import com.knowit.lectures.repository.LectureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LecturesServiceImpl implements LecturesService {

    private final LectureRepository lectureRepository;
    private final StreamBridge streamBridge;
    private final ModelMapper modelMapper;

    public LecturesServiceImpl(
            LectureRepository lectureRepository,
            StreamBridge streamBridge,
            ModelMapper modelMapper
    ) {
        this.lectureRepository = lectureRepository;
        this.streamBridge = streamBridge;
        this.modelMapper = modelMapper;
    }

    @Override
    public LectureResponseModel createLecture(
            User user,
            String name,
            String description,
            String category
    ) throws UserDoesNotExist, IOException {


        return null;
    }

    @Override
    public Lecture findById(String id) {
        return null;
    }

    @Override
    public List<Lecture> getAllLectures() {
        return null;
    }
}
