package project.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import project.harjoitustyo.domain.SignupForm;
import project.harjoitustyo.domain.User;
import project.harjoitustyo.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	//Kirjautumissivu
	
	@GetMapping("/login")
	public String login() {
		
		return "login"; //login.html
	}
	
	//Rekisteröitymissivu
	
	@GetMapping("/signup")
	public String addUser(Model model) {
		
		model.addAttribute("signupform", new SignupForm());
		
		return "signup"; //signup.html
	}
	
	@PostMapping("/saveuser")
	public String saveUser(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		
		if (!bindingResult.hasErrors()) {
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { //salasanan oikeellisuuden varmistus
				String password = signupForm.getPassword();
				BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
				String hashPassword = bcrypt.encode(password);
				
				User newUser = new User();
				newUser.setPasswordHash(hashPassword);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRole("USER");
				
				if (userRepository.findByUsername(signupForm.getUsername()) == null) { //katsotaan, onko käyttäjänimi vapaa
					userRepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup"; //signup.html
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
				return "signup"; //signup.html
			}
		} else {
			return "signup"; //signup.html
		}
		
		return "redirect:/login"; //login.html
	}
}
