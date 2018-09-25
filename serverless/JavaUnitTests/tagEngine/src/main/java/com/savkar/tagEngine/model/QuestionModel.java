package com.savkar.tagEngine.model;

import java.util.HashSet;
import java.util.Set;

import com.savkar.tagEngine.entity.Tag;
import com.savkar.tagEngine.model.TagModel.TagModelBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionModel {
	private Long id;
	private String question_title;
	private String question_formula;
	private Long question_type_id;
	private Long question_category_id;
	private Long isAnswerVisible;
	private Long tagId;
	private Integer createdBy;
	private Integer modifiedBy;
	
	
	public QuestionModel() {

	}

	public QuestionModel(Long id, String question_title, String question_formula, Long question_type_id,
			Long question_category_id, Long isAnswerVisible, Long tagId) {
		super();
		this.id = id;
		this.question_title = question_title;
		this.question_formula = question_formula;
		this.question_type_id = question_type_id;
		this.question_category_id = question_category_id;
		this.isAnswerVisible = isAnswerVisible;
		this.tagId = tagId;
	}

	public QuestionModel(Long id, String question_title, String question_formula, Long question_type_id,
			Long question_category_id, Long isAnswerVisible, Long tagId, Integer createdBy, Integer modifiedBy) {
		super();
		this.id = id;
		this.question_title = question_title;
		this.question_formula = question_formula;
		this.question_type_id = question_type_id;
		this.question_category_id = question_category_id;
		this.isAnswerVisible = isAnswerVisible;
		this.tagId = tagId;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	

}
