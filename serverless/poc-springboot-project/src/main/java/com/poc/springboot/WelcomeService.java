package com.poc.springboot;

import org.springframework.stereotype.Component;

@Component
public class WelcomeService {
	
	//Spring to manage this bean and create an instance of this
	//Tell spring to manage this by using @Component
	
		public String retreiveWelcomeMessage()
		{
			return "Good Morning with Service in diff pkg!";
		}



}
