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
import project.harjoitustyo.domain.Show;
import project.harjoitustyo.domain.ShowRepository;

@RestController
public class ShowRestController {
	
	@Autowired
	ShowRepository showRepository;
	
	//kaikki ohjelmat
	
	@GetMapping("/shows")
	public @ResponseBody List<Show> showListRest() {
		
		return (List<Show>) showRepository.findAll();
	}
	
	//yksi ohjelma id-arvolla
	
	@GetMapping("/shows/{id}")
	public @ResponseBody Optional<Show> findShowRest(@PathVariable("id") Long id) {
		
		return showRepository.findById(id);
	}
	
	//uuden ohjelman luonti
	
	@PostMapping("/shows")
	public @ResponseBody Show saveShowRest(@RequestBody Show show) {
		
		return showRepository.save(show);
	}

}
