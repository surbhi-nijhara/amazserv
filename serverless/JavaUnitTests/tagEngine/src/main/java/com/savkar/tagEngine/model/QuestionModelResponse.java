package com.savkar.tagEngine.model;

import java.util.List;

import com.savkar.tagEngine.model.QuestionModel.QuestionModelBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionModelResponse {
	private Long id;
	private String question_title;
	private String question_formula;
	private Long question_type_id;
	private Long question_category_id;
	private Long isAnswerVisible;
	private List<TagModel> tagModel;
	
	public QuestionModelResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionModelResponse(Long id, String question_title, String question_formula, Long question_type_id,
			Long question_category_id, Long isAnswerVisible, List<TagModel> tagModel) {
		super();
		this.id = id;
		this.question_title = question_title;
		this.question_formula = question_formula;
		this.question_type_id = question_type_id;
		this.question_category_id = question_category_id;
		this.isAnswerVisible = isAnswerVisible;
		this.tagModel = tagModel;
	}

	
	
	
}
