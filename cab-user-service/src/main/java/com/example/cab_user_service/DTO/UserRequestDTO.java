package com.example.cab_user_service.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserRequestDTO {

	    @NotBlank(message = "Name cannot be blank")
	    private String name;

	    @Email(message = "Email should be valid")
	    @NotBlank(message = "Email is required")
	    private String email;

	    @NotBlank(message = "Phone number is required")
	    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	    private String phone;

	    @NotBlank(message = "Password is required")
	    @Pattern(
	        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$",
	        message = "Password must be 8+ chars, include upper, lower, number & special character")
	    private String password;
}
