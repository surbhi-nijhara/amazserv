package com.savkar.tagEngine.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.savkar.tagEngine.entity.Discipline;
import com.savkar.tagEngine.repository.DisciplineRepository;


@RunWith(SpringRunner.class)
@WebMvcTest(value = DisciplineService.class, secure = false)
public class DisciplineServiceTest {
	
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
