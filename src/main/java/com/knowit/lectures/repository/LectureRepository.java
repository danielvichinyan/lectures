package com.knowit.lectures.repository;

import com.knowit.lectures.domain.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, String> {

    Lecture findByName(String name);

    List<Lecture> findAll();
}
