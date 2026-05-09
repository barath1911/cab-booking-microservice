package com.example.cab_user_service.logic;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cab_user_service.DTO.LoginRequestDTO;
import com.example.cab_user_service.DTO.UserRequestDTO;
import com.example.cab_user_service.DTO.UserResponseDTO;
import com.example.cab_user_service.model.User;
import com.example.cab_user_service.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
    private PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmailService emailService;

	public UserResponseDTO registerUser(UserRequestDTO request){
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		//User user=new User();

	//	user.setName(request.getName());
	//	user.setEmail(request.getEmail());
	//	user.setPhone(request.getPhone());
	//	user.setPassword(passwordEncoder.encode(request.getPassword()));
	//	user.setCreatedAt(LocalDateTime.now());

	//	User savedUser=userRepository.save(user);
//
	//	UserResponseDTO response=new UserResponseDTO();
	//	response.setId(savedUser.getId());
	 //   response.setName(savedUser.getName());
	 //   response.setEmail(savedUser.getEmail());

	  //  return response;


		User user=modelMapper.map(request, User.class);

		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user.setCreatedAt(LocalDateTime.now());

		User savedUser=userRepository.save(user);

		emailService.sendWelcomeEmail(savedUser.getEmail(), savedUser.getName());

		return modelMapper.map(savedUser, UserResponseDTO.class);

	}

	public String login(LoginRequestDTO request){

		User user=userRepository.findByEmail(request.getEmail())
				.orElseThrow(()->new RuntimeException("User not found"));

		if(passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			return "Login successful";
		}else {
			throw new RuntimeException("Invalid password");
		}
	}

}
