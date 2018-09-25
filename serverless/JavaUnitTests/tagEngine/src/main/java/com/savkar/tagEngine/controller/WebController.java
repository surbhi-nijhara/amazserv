package com.savkar.tagEngine.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.savkar.tagEngine.entity.Discipline;
import com.savkar.tagEngine.model.DisciplineModel;
import com.savkar.tagEngine.model.QuestionModel;
import com.savkar.tagEngine.model.QuestionModelResponse;
import com.savkar.tagEngine.model.QuestionTagModel;
import com.savkar.tagEngine.model.SubjectModel;
import com.savkar.tagEngine.model.TagModel;
import com.savkar.tagEngine.service.DisciplineService;
import com.savkar.tagEngine.service.QuestionService;
import com.savkar.tagEngine.service.SubjectService;
import com.savkar.tagEngine.service.TagService;

@RestController
@EnableWebMvc
@RequestMapping("/")

public class WebController {
	
	private static final Logger logger = LogManager.getLogger(WebController.class);
	
	@Autowired
	DisciplineService service;
	
	@Autowired
	SubjectService subjectService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	QuestionService questionService;
	
	//*************** Discipline API's ***************//
	@PostMapping("/disciplines")
	public ResponseEntity<DisciplineModel> create(@RequestBody DisciplineModel discipline){
		return new ResponseEntity(service.saveDiscipline(discipline),HttpStatus.CREATED);
	}
	
	@GetMapping("/disciplines/{discipline}")
	public ResponseEntity<List<Discipline>> getDiscipline(@PathVariable String discipline){
		return new ResponseEntity(service.getDiscipline(discipline),  HttpStatus.OK);
	}
	
	@GetMapping("/disciplines")
	public ResponseEntity<List<Discipline>> getAllDisciplines(){
		return new ResponseEntity(service.getAllDiscipline(),  HttpStatus.OK);
	}
	
	//*************** Subject API's ***************//
	@PostMapping("/subjects")
	public ResponseEntity<SubjectModel> addSubject(@RequestBody SubjectModel subject){
		return new ResponseEntity<SubjectModel>(subjectService.saveSubject(subject), HttpStatus.CREATED); 
	}
	
	@GetMapping("/subjects")
	public ResponseEntity<List<SubjectModel>> getSubject(@RequestParam(required=false) String name, @RequestParam(required=false) String discipline){
		if(name != null){
			return new ResponseEntity(subjectService.getSubjectByName(name), HttpStatus.OK);
		}
		else {
			return new ResponseEntity(subjectService.getSubjectByDisciplineName(discipline), HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/allsubjects")
	public ResponseEntity<List<SubjectModel>> getAllSubject(){
		 return new ResponseEntity(subjectService.getAllSubjects(), HttpStatus.OK);
		
	}
	
	//*************** Tag API's ***************//
	@PostMapping("/tag")
	public ResponseEntity<TagModel> addTag(@RequestBody TagModel tagModel){
		logger.debug(tagModel.toString());
		TagModel savedModel = tagService.saveTag(tagModel);
		return new ResponseEntity<TagModel>(savedModel, HttpStatus.CREATED);
	}
	
	@PutMapping("/tag/{id}")
	public ResponseEntity<TagModel> updateTagName(@PathVariable Long id, @RequestBody TagModel tagModel){
		return new ResponseEntity<TagModel>(tagService.updateTag(id, tagModel), HttpStatus.OK);
	}
	
/*	@GetMapping("/tag")
	public ResponseEntity<TagModel> getTagBySubjectName(@RequestParam(required=false) String subject_name){
		return new ResponseEntity<TagModel>(tagService.getTagBySubjectName(subject_name), HttpStatus.OK);
	}
	*/
	
	@GetMapping("/allTags")
	public ResponseEntity<List<TagModel>> getTagsByName(){			
		return new ResponseEntity<List<TagModel>>(tagService.getAllTags(), HttpStatus.OK);
	}
	
	@GetMapping("/tagName")
	public ResponseEntity<List<TagModel>> getTagsByName(@RequestParam(value="name") String name){			
		return new ResponseEntity<List<TagModel>>(tagService.getTagsByName(name), HttpStatus.OK);
	}
	
	@GetMapping("/tag")
	public ResponseEntity<List<TagModel>> getTagsByName(@RequestParam(value="name") Optional <String> name,
			@RequestParam(value="subject") Optional <String> subject,
			@RequestParam(value="discipline") Optional <String> discipline){
		return new ResponseEntity<List<TagModel>>(tagService.getTags(name,subject,discipline), HttpStatus.OK);
	}
	
	//*************** Question API's ***************//
	@PostMapping("/question")
	public ResponseEntity<QuestionModelResponse> saveQuestion(@RequestBody QuestionModel questionModel){
		return new ResponseEntity<QuestionModelResponse>(questionService.saveQuestion(questionModel), HttpStatus.CREATED);
	}
	
	@PostMapping("/tagQuestion")
	public ResponseEntity<QuestionModelResponse> addTagToQuestion(@RequestBody QuestionTagModel questionTagModel){
		return new ResponseEntity<QuestionModelResponse>(questionService.addTagToQuestion(questionTagModel), HttpStatus.CREATED);
	}
	
	@GetMapping("/questions")
	public ResponseEntity<List<QuestionModelResponse>> getQuestionsByTagName(@RequestParam String tagname){
		return new ResponseEntity<List<QuestionModelResponse>>(questionService.getQuestionsByTagName(tagname), HttpStatus.OK);
	}
	
	@DeleteMapping("/tagQuestion")
	public ResponseEntity deleteTagToQuestion(@RequestBody QuestionTagModel questionTagModel){
		questionService.deleteTagToQuestion(questionTagModel);
		return new ResponseEntity(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/tags")
	public ResponseEntity<QuestionModelResponse> getAllTagsOfQuestion(@RequestParam Long questionId){
		return new ResponseEntity<QuestionModelResponse>(questionService.getAllTagsOfQuestion(questionId), HttpStatus.OK);
	}

	
}


