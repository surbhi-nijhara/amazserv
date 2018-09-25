package com.savkar.tagEngine.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.annotation.Propagation;

import com.savkar.tagEngine.entity.Discipline;
//import com.savkar.tagEngine.entity.DisciplineRepository;


@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@AutoConfigureTestDatabase
public class DisciplineRepositoryTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	DisciplineRepository repository;
	
	@Test
	public void testFindByName() throws Exception {
		
		testEntityManager.persist(new Discipline("Mathematics"));
		testEntityManager.flush();
		//User user = this.repository.findByUsername("sboot");
		//assertThat(user.getUsername()).isEqualTo("sboot");
		//assertThat(user.getVin()).isEqualTo("1234");
		
		List<Discipline> disciplines = this.repository.findByName("Mathematics");
		assertEquals(1, disciplines.size());
	
	}
	
	//List<Discipline> findByName(String name); //actual method 

}
