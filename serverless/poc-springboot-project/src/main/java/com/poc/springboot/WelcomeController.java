package com.poc.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class WelcomeController {
	
	
	//private WelcomeService service = new WelcomeService();
	//Autowire(Spring creates bean and inh=jects whereever needed))
	@Autowired
	private WelcomeService service;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return service.retreiveWelcomeMessage();
	}


}
/*
//Spring to manage this bean and create an instance of this
//Tell spring to manage this by using @Component
@Component
class WelcomeService{
	
	
	
	public String retreiveWelcomeMessage()
	{
		return "Good Morning with Annotations!";
	}
}
*/
