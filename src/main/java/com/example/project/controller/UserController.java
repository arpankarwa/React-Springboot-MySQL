package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepository repository;
	

//--------------------------------------------------------------------------

	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) {
		
		return repository.save(user);
	}
	
	@PostMapping("/addUsers")
	public List<User> addUsers(@RequestBody List<User> users){
		
		return repository.saveAll(users);
	}

//--------------------------------------------------------------------------
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers(){
		
		return repository.findAll();
	}
	
	@GetMapping("/getUserById/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		
		return repository.findById(id);
    }
	
//--------------------------------------------------------------------------
	
	@PutMapping("/updateUser/{id}")
	public User updateUser(@RequestBody User newuser, @PathVariable Long id){
		
		return repository.findById(id)
				.map(user -> {
					user.setName(newuser.getName());
					user.setUsername(newuser.getUsername());
					user.setEmail(newuser.getEmail());
					
					return repository.save(user);
				}).orElseThrow(null);
	}

//--------------------------------------------------------------------------
	
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable Long id) {
		
		if(repository.existsById(id)){
			repository.deleteById(id);
			return "User with id :- "+ id + " is removed.";
		}
		else {
			return "User with id :- "+ id + " is not present.";
		}
	}
	
//--------------------------------------------------------------------------
	
}
