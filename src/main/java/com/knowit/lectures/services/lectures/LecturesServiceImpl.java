package com.knowit.lectures.services.lectures;

import com.knowit.lectures.domain.entities.Lecture;
import com.knowit.lectures.domain.entities.User;
import com.knowit.lectures.domain.models.LectureResponseModel;
import com.knowit.lectures.domain.models.LectureVideoRequestModel;
import com.knowit.lectures.exceptions.UserDoesNotExist;
import com.knowit.lectures.repository.LectureRepository;
import com.knowit.lectures.repository.RoleRepository;
import com.knowit.lectures.services.users.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LecturesServiceImpl implements LecturesService {

    private final LectureRepository lectureRepository;
    private final StreamBridge streamBridge;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final RoleRepository roleRepository;

    public LecturesServiceImpl(
            LectureRepository lectureRepository,
            StreamBridge streamBridge,
            ModelMapper modelMapper,
            UserService userService,
            RoleRepository roleRepository
    ) {
        this.lectureRepository = lectureRepository;
        this.streamBridge = streamBridge;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Override
    public LectureResponseModel createLecture(
            User user,
            String name,
            String description,
            String category
    ) throws UserDoesNotExist, IOException {
        Lecture lecture = new Lecture();
        lecture.setName(name);
        lecture.setDescription(description);
        lecture.setCategory(category);

        this.lectureRepository.saveAndFlush(lecture);

        // Returned to the front-end
        LectureResponseModel responseModel = new LectureResponseModel();
        responseModel.setName(lecture.getName());
        responseModel.setDescription(lecture.getDescription());
        responseModel.setCategory(lecture.getCategory());

        // Takes care of the video
        LectureVideoRequestModel lectureRequestModel = new LectureVideoRequestModel();
        Lecture lectureSendId = this.lectureRepository.findByName(name);
        lectureRequestModel.setId(lectureSendId.getId());
        lectureRequestModel.setName(lectureSendId.getName());

        return responseModel;
    }

    @Override
    public Lecture findById(String id) {
        return this.lectureRepository.findById(id).orElseThrow();
    }

    @Override
    public LectureResponseModel findByName(String name) {
        Lecture lecture = this.lectureRepository.findByName(name);
        LectureResponseModel lectureResponseModel = new LectureResponseModel();

        lectureResponseModel.setName(lecture.getName());
        lectureResponseModel.setDescription(lecture.getName());
        lectureResponseModel.setCategory(lecture.getCategory());

        return lectureResponseModel;
    }

    @Override
    public List<LectureResponseModel> getAllLectures() {
        List<Lecture> lectureList = this.lectureRepository.findAll();
        List<LectureResponseModel> mappedList = new ArrayList<>();

        lectureList.forEach(lecture -> {
            LectureResponseModel lectureResponseModel = new LectureResponseModel();
            lectureResponseModel.setName(lecture.getName());
            lectureResponseModel.setDescription(lecture.getDescription());
            lectureResponseModel.setCategory(lecture.getCategory());
            mappedList.add(lectureResponseModel);
        });

        return mappedList;
    }
}
