package com.savkar.tagEngine.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savkar.tagEngine.entity.QuestionCategory;
import com.savkar.tagEngine.repository.QuestionCategoryRepository;

@Service
public class QuestionCategoryService {
	@Autowired
	QuestionCategoryRepository questionCategoryRepository;
	
	public Optional<QuestionCategory> findByQuestionCategoryId(Long question_category_id) {
		return questionCategoryRepository.findById(question_category_id);
	}
}
