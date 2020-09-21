package com.example.demo;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	// basic hello message
	@GetMapping("/hello")
	@ResponseBody
	public String sayHello() {
		return "Hello!!";
	}
	
	
	// return HTML content
	@GetMapping(value = "hello-java.html", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String helloHtml() {
		int val = 7;
		return "<html><p>Hello din Java " + val + "</p></html>";
	}
	
	// return current date
	@GetMapping(value = "date.html", produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String indexHtml() {
		Date currentDate = new Date();
		
		return "<html><p>Data curenta este " + currentDate.toString() + "</p></html>";
	}
	
	// hello message with parameter
	@GetMapping("/hello-name")
	@ResponseBody
	public String sayHelloName(@RequestParam(name="nume", required=true) int name) {
		return "Hello, " + name + "!!";
	}
	
	// model and view
	@GetMapping("/hello-model")
//	@ResponseBody // nu mai avem nevoie de aceasta adnotare
	public ModelAndView sayHelloModel() {
		Date currentDate = new Date();
		ModelAndView helloModel = new ModelAndView("hello-model.html");
		helloModel.addObject("date", currentDate.toString());
		
		return helloModel;
	}
	
}
