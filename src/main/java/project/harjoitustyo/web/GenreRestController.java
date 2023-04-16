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
import project.harjoitustyo.domain.Genre;
import project.harjoitustyo.domain.GenreRepository;

@RestController
public class GenreRestController {
	
	@Autowired
	GenreRepository genreRepository;
	
	//kaikki genret
	
	@GetMapping("/genres")
	public @ResponseBody List<Genre> genreListRest() {
		
		return (List<Genre>) genreRepository.findAll();
	}
	
	//yksi genre id-arvolla
	
	@GetMapping("/genres/{id}")
	public @ResponseBody Optional<Genre> findGenreRest(@PathVariable("id") Long genreid) {
		
		return genreRepository.findById(genreid);
	}
	
	//uuden genren luonti
	
	@PostMapping("/genres")
	public @ResponseBody Genre saveGenreRest(@RequestBody Genre genre) {
		
		return genreRepository.save(genre);
	}
}
