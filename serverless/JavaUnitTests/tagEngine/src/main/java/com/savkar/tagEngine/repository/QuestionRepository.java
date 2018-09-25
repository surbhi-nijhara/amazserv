package com.savkar.tagEngine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.savkar.tagEngine.entity.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	
	@Query("select question FROM Question question join question.tags tag where tag.name like :tagName")
	public List<Question> getQuestionByTagName(String tagName);

}
