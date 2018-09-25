package com.savkar.tagEngine.model;

import com.savkar.tagEngine.model.QuestionModel.QuestionModelBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionTagModel {
	private Long id;
	private Long questionId;
	private Long tagId;
	
	public QuestionTagModel() {

	}

	public QuestionTagModel(Long id, Long questionId, Long tagId) {
		super();
		this.id = id;
		this.questionId = questionId;
		this.tagId = tagId;
	}


	
	
	
}
