package com.example.demo.restcontrolers;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CandidatureDto;
import com.example.demo.modele.Candidature;
import com.example.demo.modele.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.candidatureService;
import com.example.demo.service.userService;
import com.example.demo.service.register.RegistrationRequest;
//import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/user")
public class UserRESTController {


	@Autowired
	UserRepository userrep;
	@Autowired
	candidatureService candi;
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
	@PutMapping
	public User registerupdate(@RequestBody RegistrationRequest request) {
		return userservice.registerUser(request);	
	}
	@PostMapping("/registerAdmin")
	public User registerAdmin(@RequestBody RegistrationRequest request) {
		return userservice.registerUserAdmin(request);	
	}
	@GetMapping("/candi")
	public List<CandidatureDto> getall(){
	return candi.getAllcandidatureUser();
	}
	 @GetMapping(value = "/alll", produces = "application/json")
	   public List<Candidature> getMethodName() {
	       return candi.getAll();
	   }
	@GetMapping("/admins")
	public ResponseEntity<List<User>> getAllAdmins() {
		List<User> admins = userservice.findAllUsers();
		return ResponseEntity.ok(admins);
	}
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userservice.findAllEtudUsers();
		return ResponseEntity.ok(users);


	}
	
}
