package com.savkar.tagEngine;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.savkar.tagEngine.controller.WebController;


/*commands
 *  test -P springbootapp -Dtest=TagEngineApplicationTests 
 *  -Dtest=WebControllerTest
 * 
 * 
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagEngineApplicationTest{
	
	//@Autowired
	//private WebController controller;

	@Test
	public void contextLoads() throws Exception{		
		  //assertThat(controller).isNotNull();
	}

}


