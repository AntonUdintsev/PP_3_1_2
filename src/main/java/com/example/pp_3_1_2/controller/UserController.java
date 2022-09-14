package com.example.pp_3_1_2.controller;


import com.example.pp_3_1_2.model.User;
import com.example.pp_3_1_2.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class UserController {
	private final UserService userService;


	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/user")
	public String printUser(ModelMap model, @AuthenticationPrincipal User user) {
		model.addAttribute("user", user);
		return "/user";
	}
}