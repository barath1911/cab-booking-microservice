package com.example.cab_user_service.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequestDTO {

	 @NotBlank(message = "Email is required")
	 @Email(message = "Enter valid email")
	private String email;

	 @NotBlank(message = "Password is required")
	  @Pattern(
	    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
	   message = "Password must be 8+ chars, include upper, lower, number & special character")
    private String password;
}
