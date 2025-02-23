package com.example.demo.service;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.email.EmailSender;
import com.example.demo.modele.Role;
import com.example.demo.modele.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.exceptions.EmailAlreadyExistsException;
import com.example.demo.service.exceptions.ExpiredTokenException;
import com.example.demo.service.exceptions.InvalidTokenException;
import com.example.demo.service.register.RegistrationRequest;
import com.example.demo.service.register.VerificationToken;
import com.example.demo.service.register.VerificationTokenRepository;

@Transactional
@Service
public class Userimplservice implements userService {
	@Autowired
	UserRepository userrepository;
	@Autowired
	EmailSender emailSender;
	@Autowired
	VerificationTokenRepository verificationTokenRepo;
	@Autowired
	RoleRepository rolerepository;
	@Autowired
	BCryptPasswordEncoder bcryptpwd;
	@Override
	public User saveUser(User user) {
		user.setPassword(bcryptpwd.encode(user.getPassword()));
		return userrepository.save(user);
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userrepository.findByUsername(username);
	}

	@Override
	public Role addRole(Role role) {
		// TODO Auto-generated method stub
		return rolerepository.save(role);
	}

	@Override
	public User addRoleToUser(String username, String rolename) {
		User usr = userrepository.findByUsername(username);
		Role role = rolerepository.findByRole(rolename);
		usr.getRoles().add(role);
		//userrepository.save(usr);
		return usr ;
	}

	@Override
	public List<User> findAllUsers() {

		return userrepository.findAll();
	}

	@Override
	public User registerUser(RegistrationRequest request) {
		Optional<User> optinaluser = userrepository.findByEmail(request.getEmail());
		if(optinaluser.isPresent())
		{
			throw new EmailAlreadyExistsException("Email déjà existant!");

		}
		User newUser = new User();
		newUser.setUsername(request.getUsername());
		newUser.setEmail(request.getEmail());
		newUser.setNom(request.getNom());
		newUser.setPrenom(request.getPrenom());
		newUser.setTelephone(request.getTelephone());
		newUser.setPassword( bcryptpwd.encode(request.getPassword()));
		newUser.setEnabled(false);
		userrepository.save(newUser);

		Role r = rolerepository.findByRole("USER");
		if (r == null) {
			r = new Role("USER");
			rolerepository.save(r);
		}

		List<Role> roles = new ArrayList<>();
		roles.add(r);
		newUser.setRoles(roles);
		//génére le code secret
		String code = this.generateCode();

		VerificationToken token = new VerificationToken(code, newUser);
		verificationTokenRepo.save(token);
		//envoie email à user

		sendEmailUser(newUser,token.getToken());
		return userrepository.save(newUser);
	}
	@Override
	public User registerUserAdmin(RegistrationRequest request) {
		Optional<User> optinaluser = userrepository.findByEmail(request.getEmail());
		if(optinaluser.isPresent())
		{
			throw new EmailAlreadyExistsException("Email déjà existant!");

		}
		User newUser = new User();
		newUser.setUsername(request.getUsername());
		newUser.setEmail(request.getEmail());
		newUser.setPassword( bcryptpwd.encode(request.getPassword()));
		newUser.setEnabled(false);
		userrepository.save(newUser);

		Role r = rolerepository.findByRole("ADMIN");
		if (r == null) {
			r = new Role("ADMIN");
			rolerepository.save(r);
		}
		List<Role> roles = new ArrayList<>();
		roles.add(r);
		newUser.setRoles(roles);

		//génére le code secret
		String code = this.generateCode();

		VerificationToken token = new VerificationToken(code, newUser);
		verificationTokenRepo.save(token);
		//envoie email à user

		sendEmailUser(newUser,token.getToken());
		return userrepository.save(newUser);
	}


	private String generateCode() {
		Random random = new Random();
		Integer code = 100000 + random.nextInt(900000);

		return code.toString();
	}
	@Override
	public void sendEmailUser(User u, String code) {
		String emailBody ="Bonjour "+ "<h1>"+u.getUsername() +"</h1>" +
				" Votre code de validation est "+"<h1>"+code+"</h1>";
		emailSender.sendEmail(u.getEmail(), emailBody);
	}

	@Override
	public User validateToken(String code) {
		VerificationToken token = verificationTokenRepo.findByToken(code);
		if(token == null){
			throw new InvalidTokenException("Invalid Token");
		}

		User user = token.getUser();
		Calendar calendar = Calendar.getInstance();
		if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
			verificationTokenRepo.delete(token);
			throw new ExpiredTokenException("expired Token");
		}
		user.setEnabled(true);
		userrepository.save(user);
		return user;

	}



}
