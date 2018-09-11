package com.example.postgresdemo.service;

package com.savkar.tagEngine.service;

/*
 * Sample Springboot Service layer testing
 * https://www.baeldung.com/spring-boot-testing 
 * Assertions using Hamcrest lib 
 * Ref: https://www.mkyong.com/unittest/junit-how-to-test-a-list/ 
 * 
 * Note: Will have to update the below for this app
 * */

//import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;





@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(value = DisciplineService.class, secure = false)
public class DisciplineServiceTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private DisciplineService dicsServinceInst;
	
	
    @MockBean
	DisciplineRepository repository;
    
    /*@TestConfiguration
    static class DisciplineServiceTestContextConfiguration {
  
        @Bean
        public DisciplineService discService() {
            return new DisciplineService();
        }
    }
    */
    
	/*public DisciplineModel saveDiscipline (DisciplineModel disciplineModel) {
		Discipline discipline = translateToDiscipline(disciplineModel);
		return translateToDisciplineModel(repository.save(discipline));
		
	}
	

	
	public Discipline translateToDiscipline(DisciplineModel disciplineModel) {
		if(disciplineModel.getId() != null) {
			return new Discipline(disciplineModel.getId(),disciplineModel.getName());
		}
		else {
			return new Discipline(disciplineModel.getName());
		}
	}
	
	public DisciplineModel translateToDisciplineModel(Discipline discipline) {
		return DisciplineModel.builder().id(discipline.getId()).name(discipline.getName()).build();
	}

	public List<Discipline> getDiscipline(String discipline) {
		return repository.findByName(discipline);
	}*/
	@Test
	public void testGetAllDiscipline() throws Exception {
		
		List<Discipline> mockDiscipline = new ArrayList<Discipline>();
		Discipline d1 = new Discipline(4, "testName"); 
		mockDiscipline.add(d1);
				
		Mockito.when(repository.findAll())
		.thenReturn(mockDiscipline);
						
		List<Discipline> allDisciplines = dicsServinceInst.getAllDiscipline();
		//1. Test equal.
		assertThat(allDisciplines, is(mockDiscipline));
		 //2. If List has this value?
        assertThat(allDisciplines, hasItems(new Discipline(4, "testName")));
        //3. Check List Size
        assertThat(allDisciplines, hasSize(1));
		
        //4. check empty list
        assertThat(allDisciplines, not(IsEmptyCollection.empty()));       

	}
}
