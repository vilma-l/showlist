package project.harjoitustyo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.harjoitustyo.domain.User;
import project.harjoitustyo.domain.UserRepository;

@RestController
public class UserRestController {
	
	@Autowired
	UserRepository userRepository;
	
	//kaikki käyttäjät
	
	@GetMapping("/users")
	public @ResponseBody List<User> userListRest() {
		
		return (List<User>) userRepository.findAll();
	}
	
	//yksi käyttäjä id-arvolla
	
	@GetMapping("/users/{id}")
	public @ResponseBody Optional<User> findUserRest(@PathVariable("id") Long id) {
		
		return userRepository.findById(id);
	}
	
	//uuden käyttäjän luonti
	
	@PostMapping("/users")
	public @ResponseBody User saveUserRest(@RequestBody User user) {
		
		return userRepository.save(user);
	}
}
