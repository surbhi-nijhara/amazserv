package com.example.postgresdemo.service;

import com.example.postgresdemo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionServiceTest extends JpaRepository<Question, Long> {
}