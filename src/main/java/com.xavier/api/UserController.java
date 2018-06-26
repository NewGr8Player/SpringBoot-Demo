package com.xavier.api;

import com.xavier.bean.User;
import com.xavier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/id/{id}")
	public User findById(@PathVariable("id") String id){
		return this.userService.searchById(id);
	}
}
