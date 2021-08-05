package com.knowit.lectures.repository;

import com.knowit.lectures.domain.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, String> {

    Quiz findByName(String name);

    List<Quiz> findAll();
}
