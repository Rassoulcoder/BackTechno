package com.example.demo.restcontrolers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modele.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.userService;
import com.example.demo.service.register.RegistrationRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserRESTController {


	@Autowired
	UserRepository userrep;

	@Autowired
	userService userservice;

	@GetMapping("/all")
	public List<User> getAllUsers(){
		return userrep.findAll();
	}
	@GetMapping("/verifyEmail/{token}")
	public User verifyEmail(@PathVariable("token") String token){
		return userservice.validateToken(token);
	}

	@PostMapping
	public User register(@RequestBody RegistrationRequest request) {
		return userservice.registerUser(request);	
	}
	@PostMapping("/registerAdmin")
	public User registerAdmin(@RequestBody RegistrationRequest request) {
		return userservice.registerUserAdmin(request);	
	}

}
