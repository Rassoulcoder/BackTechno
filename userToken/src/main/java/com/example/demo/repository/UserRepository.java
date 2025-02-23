package com.example.demo.repository;

//import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modele.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	Optional<User> findByEmail(String email);
    Optional<User> findByUserid(Long user_id);

}
