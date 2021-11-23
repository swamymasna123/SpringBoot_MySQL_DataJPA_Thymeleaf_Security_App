package com.swamy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swamy.dto.UserDTO;
import com.swamy.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	
	@GetMapping("/register")
	public String showUserRegPage(Model model) {
		model.addAttribute("user", new UserDTO());
		return "UserRegister";
	}

	@PostMapping("/save")
	public String saveUserData(@ModelAttribute UserDTO user , Model model) {
		
		Integer id = userService.saveUser(user);
		String message = "User Data '"+ id +"' Saved Successfully";
		model.addAttribute("message", message);
		model.addAttribute("user", new UserDTO());
		List<UserDTO> list = userService.getAllUsers();
		model.addAttribute("list", list);
		return "UserData";
	}
	
	@GetMapping("/all")
	public String getAllUsersData(Model model) {
		List<UserDTO> list = userService.getAllUsers();
		model.addAttribute("list", list);
		return "UserData";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUserById(@PathVariable Integer id , Model model) {
		
		userService.deleteUser(id);
		String message = "User Data '"+ id +"' Deleted Successfully";
		List<UserDTO> list = userService.getAllUsers();
		model.addAttribute("message", message);
		model.addAttribute("list", list);
		return "UserData";

	}
	
	@GetMapping("/edit/{id}")
	public String editUserById(@PathVariable Integer id , Model model) {
		UserDTO user = userService.getOneUser(id);
		model.addAttribute("user", user);
		return "UserEdit";
	}
	
	@PostMapping("/update")
	public String updateUserData(@ModelAttribute UserDTO user , Model model) {
		
		userService.updateUser(user);
		String message = "User Data '"+ user.getUserId() +"' Updated Successfully";
		model.addAttribute("message", message);
		model.addAttribute("user", new UserDTO());
		List<UserDTO> list = userService.getAllUsers();
		model.addAttribute("list", list);
		return "UserData";
	}
	
	@GetMapping("/view/{id}")
	public String viewUserById(@PathVariable Integer id , Model model) {
		UserDTO user = userService.getOneUser(id);
		model.addAttribute("ob", user);
		return "UserView";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	
	@GetMapping("/common")
	public String commonPage() {
		return "common";
	}

	@GetMapping("/denied")
	public String deniedPage() {
		return "denied";
	}
}











