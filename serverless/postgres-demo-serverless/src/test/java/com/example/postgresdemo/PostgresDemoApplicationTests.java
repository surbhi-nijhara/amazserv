package com.example.postgresdemo;

/*
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

}
*/

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/*commands
 *  test -P springbootapp -Dtest=TagEngineApplicationTests 
 *  -Dtest=WebControllerTest
 * 
 * 
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresDemoApplicationTests{
	
	/*@Autowired
	private QuestionController controller;*/

	@Test
	public void contextLoads() throws Exception{		
		 // assertThat(controller).isNotNull();
	}

}


