package com.swamy.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swamy.dto.UserDTO;
import com.swamy.service.IUserService;

@RestController
@RequestMapping("/user_api")
public class UserRestController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("/save")
	public ResponseEntity<String>saveUser(@RequestBody UserDTO userDTO){
		String body = null;
		Integer id = userService.saveUser(userDTO);
		body = "User Data '"+id+"' Saved Successfully";
		return new ResponseEntity<String>(body, HttpStatus.CREATED);
	}
}









