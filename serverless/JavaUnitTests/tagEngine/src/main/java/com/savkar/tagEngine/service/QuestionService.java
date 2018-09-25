package com.savkar.tagEngine.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savkar.tagEngine.entity.Question;
import com.savkar.tagEngine.entity.QuestionCategory;
import com.savkar.tagEngine.entity.QuestionType;
import com.savkar.tagEngine.entity.Subject;
import com.savkar.tagEngine.entity.Tag;
import com.savkar.tagEngine.model.QuestionModel;
import com.savkar.tagEngine.model.QuestionModelResponse;
import com.savkar.tagEngine.model.QuestionTagModel;
import com.savkar.tagEngine.model.TagModel;
import com.savkar.tagEngine.repository.QuestionRepository;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	QuestionTypeService questionTypeService;
	
	@Autowired
	QuestionCategoryService questionCategoryService;
	
	@Autowired
	TagService tagService;
	
	public QuestionModelResponse saveQuestion(QuestionModel questionModel) {
		boolean isAnswerVisible = false;
		Optional<QuestionType> questionType = questionTypeService.findByQuestionId(questionModel.getQuestion_type_id());
		Optional<QuestionCategory> questionCategory = questionCategoryService.findByQuestionCategoryId(questionModel.getQuestion_category_id());
		if(questionType.get() != null && questionCategory.get() != null) {
			Set<Tag> tagsToSave = null;
			if(questionModel.getTagId() != null) {
				Long tagId = questionModel.getTagId();
				tagsToSave = new HashSet<Tag>();
				Optional<Tag> tagObject = tagService.getTagById(tagId);
				if(tagObject.get() != null) {
					tagsToSave.add(tagObject.get());
				}
			}
			Question question = translateToQuestionEntity(questionModel, questionType.get(), questionCategory.get(), tagsToSave, isAnswerVisible);
			Question savedQuestion = questionRepository.save(question);
			return translateToQuestionModelResponse(savedQuestion,false);
		}
		else {
			return null;
		}
	}
	
	public QuestionModelResponse addTagToQuestion(QuestionTagModel questionTagModel) {
		Optional<Question> question = questionRepository.findById(questionTagModel.getQuestionId());
		if(question.get() != null) {
			Question questionObject = question.get();
			Optional<Tag> tagObject = tagService.getTagById(questionTagModel.getTagId());
			Set<Tag> tagList = questionObject.getTags();
			tagList.add(tagObject.get());
			Question savedQuestion = questionRepository.save(questionObject);
			return translateToQuestionModelResponse(savedQuestion, false);
		}
		else {
			return null;
		}
	}
	
	public void deleteTagToQuestion(QuestionTagModel questionTagModel) {
		Optional<Question> question = questionRepository.findById(questionTagModel.getQuestionId());
		if(question.get() != null) {
			Question questionObject = question.get();
			Optional<Tag> tagObject = tagService.getTagById(questionTagModel.getTagId());
			questionObject.getTags().remove(tagObject.get());
			questionRepository.save(questionObject);
		}
	}
	
	public List<QuestionModelResponse> getQuestionsByTagName(String tagName) {
		List<Question> questions = questionRepository.getQuestionByTagName("%"+tagName+"%");
		return questions.stream().map(question -> translateToQuestionModelResponse(question, false)).collect(Collectors.toList());
	}
	
	public QuestionModelResponse getAllTagsOfQuestion(Long questionID){
		Question question = questionRepository.findById(questionID).get();
		if(question != null) {
			//Set<Tag> tagListOfQuestion = question.getTags();
			return translateToQuestionModelResponse(question, false);
		}
		else {
			return null;	
		}
		
	}
	
	private Question translateToQuestionEntity(QuestionModel questionModel, QuestionType questionType, QuestionCategory questionCategory, Set<Tag> tagList, boolean isAnswerVisible){
		return new Question(questionModel.getQuestion_title(), questionModel.getQuestion_formula(), questionType, questionCategory, tagList, questionModel.getCreatedBy(), questionModel.getModifiedBy(), isAnswerVisible);
	}
	
	
	private QuestionModelResponse translateToQuestionModelResponse(Question question, boolean onlyTags){
		QuestionModelResponse model = QuestionModelResponse.builder().build();
		Set<Tag> tagList = question.getTags(); 
		List<TagModel> modelList = new ArrayList<TagModel>();
		if(tagList!=null && tagList.size() > 0) {
			for (Tag tag : tagList) {
				modelList.add(tagService.translateToTagModel(tag));
				
			}
		}
		model.setTagModel(modelList);
		if(!onlyTags) {
			model.setId(question.getId());
			model.setQuestion_title(question.getQuestion_title());
			model.setQuestion_formula(question.getQuestion_formula());
			model.setQuestion_type_id(question.getQuestionType().getId());
			model.setQuestion_category_id(question.getQuestionCategory().getId());
		}
		
		return model;
		
	}
}
