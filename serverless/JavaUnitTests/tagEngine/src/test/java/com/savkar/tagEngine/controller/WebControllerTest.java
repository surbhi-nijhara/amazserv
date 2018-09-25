package com.savkar.tagEngine.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.savkar.tagEngine.entity.Discipline;
import com.savkar.tagEngine.service.DisciplineService;
import com.savkar.tagEngine.service.QuestionService;
import com.savkar.tagEngine.service.SubjectService;
import com.savkar.tagEngine.service.TagService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = WebController.class, secure = false)
public class WebControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	DisciplineService disciplineService;
	
	@MockBean
	SubjectService subjectService;
	
	@MockBean
	TagService tagService;
	
	@MockBean
	QuestionService questionService;
	
	
	
	//@MockBean
	//private SubjectService subjectService;
	//
	/*@Autowired
	SubjectService subjectService;
	
	@Autowired
	TagService tagService;
	
	@Autowired
	QuestionService questionService;*/
	
	//*************** Discipline API's ***************//




	//List<Discipline> mockAllDisciplines = Arrays.asList(array) ;
	
	
	/*@Test
	public void testCreateDiscipline(){
		//return new ResponseEntity(service.saveDiscipline(discipline),HttpStatus.CREATED);
	}*/
	
	@Test
	public void testGetDiscipline() throws Exception{
		List<Discipline> mockDiscipline = new ArrayList<Discipline>();
		Discipline d1 = new Discipline(4, "testName"); 
		mockDiscipline.add(d1);
		
		Mockito.when(disciplineService.getDiscipline(Mockito.anyString()))
				.thenReturn(mockDiscipline);
		
		/*RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/disciplines/Biology").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		System.out.println("MvcResult is ::"+result.getResponse());
		
		String expected = "[{id:4,name:testName}]";
		JSONAssert.assertEquals(expected, 
								result.getResponse().getContentAsString(), false);*/
		
		this.mockMvc.perform(get("/disciplines/Biology"))
		.andExpect(status().isOk())
		.andExpect(content().string("[{\"id\":4,\"name\":\"testName\"}]"));
	}
	
	/*@Test
	public void testGetAllDisciplines(){
		//Mockito.when(
			//	disciplineService.getAllDiscipline().thenReturn(mockAllDisciplines);
	}*/
	
	//*************** Subject API's ***************//
//	@PostMapping("/subjects")
//	public ResponseEntity<SubjectModel> addSubject(@RequestBody SubjectModel subject){
//		return new ResponseEntity<SubjectModel>(subjectService.saveSubject(subject), HttpStatus.CREATED); 
//	}
//	
//	@GetMapping("/subjects")
//	public ResponseEntity<List<SubjectModel>> getSubject(@RequestParam(required=false) String name, @RequestParam(required=false) String discipline){
//		if(name != null){
//			return new ResponseEntity(subjectService.getSubjectByName(name), HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity(subjectService.getSubjectByDisciplineName(discipline), HttpStatus.OK);
//		}
//		
//	}
//	
//	@GetMapping("/allsubjects")
//	public ResponseEntity<List<SubjectModel>> getAllSubject(){
//		 return new ResponseEntity(subjectService.getAllSubjects(), HttpStatus.OK);
//		
//	}
//	
//	//*************** Tag API's ***************//
//	@PostMapping("/tag")
//	public ResponseEntity<TagModel> addTag(@RequestBody TagModel tagModel){
//		logger.debug(tagModel.toString());
//		TagModel savedModel = tagService.saveTag(tagModel);
//		return new ResponseEntity<TagModel>(savedModel, HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/tag/{id}")
//	public ResponseEntity<TagModel> updateTagName(@PathVariable Long id, @RequestBody TagModel tagModel){
//		return new ResponseEntity<TagModel>(tagService.updateTag(id, tagModel), HttpStatus.OK);
//	}
//	
///*	@GetMapping("/tag")
//	public ResponseEntity<TagModel> getTagBySubjectName(@RequestParam(required=false) String subject_name){
//		return new ResponseEntity<TagModel>(tagService.getTagBySubjectName(subject_name), HttpStatus.OK);
//	}
//	*/
//	
//	@GetMapping("/allTags")
//	public ResponseEntity<List<TagModel>> getTagsByName(){			
//		return new ResponseEntity<List<TagModel>>(tagService.getAllTags(), HttpStatus.OK);
//	}
//	
//	@GetMapping("/tagName")
//	public ResponseEntity<List<TagModel>> getTagsByName(@RequestParam(value="name") String name){			
//		return new ResponseEntity<List<TagModel>>(tagService.getTagsByName(name), HttpStatus.OK);
//	}
//	
//	@GetMapping("/tag")
//	public ResponseEntity<List<TagModel>> getTagsByName(@RequestParam(value="name") Optional <String> name,
//			@RequestParam(value="subject") Optional <String> subject,
//			@RequestParam(value="discipline") Optional <String> discipline){
//		return new ResponseEntity<List<TagModel>>(tagService.getTags(name,subject,discipline), HttpStatus.OK);
//	}
//	
//	//*************** Question API's ***************//
//	@PostMapping("/question")
//	public ResponseEntity<QuestionModelResponse> saveQuestion(@RequestBody QuestionModel questionModel){
//		return new ResponseEntity<QuestionModelResponse>(questionService.saveQuestion(questionModel), HttpStatus.CREATED);
//	}
//	
//	@PostMapping("/tagQuestion")
//	public ResponseEntity<QuestionModelResponse> addTagToQuestion(@RequestBody QuestionTagModel questionTagModel){
//		return new ResponseEntity<QuestionModelResponse>(questionService.addTagToQuestion(questionTagModel), HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/questions")
//	public ResponseEntity<List<QuestionModelResponse>> getQuestionsByTagName(@RequestParam String tagname){
//		return new ResponseEntity<List<QuestionModelResponse>>(questionService.getQuestionsByTagName(tagname), HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/tagQuestion")
//	public ResponseEntity deleteTagToQuestion(@RequestBody QuestionTagModel questionTagModel){
//		questionService.deleteTagToQuestion(questionTagModel);
//		return new ResponseEntity(HttpStatus.ACCEPTED);
//	}
//	
//	@GetMapping("/tags")
//	public ResponseEntity<QuestionModelResponse> getAllTagsOfQuestion(@RequestParam Long questionId){
//		return new ResponseEntity<QuestionModelResponse>(questionService.getAllTagsOfQuestion(questionId), HttpStatus.OK);
//	}

	
}


