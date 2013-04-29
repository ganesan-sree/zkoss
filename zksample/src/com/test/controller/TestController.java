package com.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/loginjsp")

public class TestController {//extends SelectorComposer<Component> {
	
	//@Wire
	//private Textbox titleTextbox;
	
	
	//@Listen("onClick = #submitButton")
	@RequestMapping(method = RequestMethod.POST)
	public String doGet(HttpServletRequest request) {
		System.out.println("******************************************");
System.out.println(request.getParameter("titleTextbox"));
		//System.out.println(titleTextbox);
		return "result.jsp";
	}

}
