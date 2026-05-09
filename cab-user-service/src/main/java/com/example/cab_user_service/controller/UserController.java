package com.example.cab_user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cab_user_service.DTO.LoginRequestDTO;
import com.example.cab_user_service.DTO.UserRequestDTO;
import com.example.cab_user_service.DTO.UserResponseDTO;
import com.example.cab_user_service.logic.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController{

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public UserResponseDTO register(@Valid @RequestBody UserRequestDTO requestDTO) {
		return userService.registerUser(requestDTO);
	}

	@PostMapping("/login")
	public String login(@Valid @RequestBody LoginRequestDTO request) {
		return userService.login(request);
	}
}
