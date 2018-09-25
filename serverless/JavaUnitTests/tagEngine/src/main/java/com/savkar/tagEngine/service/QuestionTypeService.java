package com.savkar.tagEngine.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savkar.tagEngine.entity.QuestionType;
import com.savkar.tagEngine.repository.QuestionRepository;
import com.savkar.tagEngine.repository.QuestionTypeRepository;

@Service
public class QuestionTypeService {
	@Autowired
	QuestionTypeRepository questionTypeRepository;
	
	public Optional<QuestionType> findByQuestionId(Long question_id) {
		return questionTypeRepository.findById(question_id);
	}
}
