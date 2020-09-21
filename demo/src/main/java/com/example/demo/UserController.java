package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	ArrayList<String> users = new ArrayList<String>();
	
	@GetMapping("/users")
	public ArrayList<String> getUsers() {
		return users;
	}
	
	private void addUser(String name) {
		this.users.add(name);
	}
	
	
}
