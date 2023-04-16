package project.harjoitustyo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import project.harjoitustyo.domain.Genre;
import project.harjoitustyo.domain.GenreRepository;

@Controller
public class GenreController {
	
	@Autowired
	private GenreRepository genreRepository;
	
	//listausnäkymä
	
	@GetMapping("/genrelist")
	public String getGenres(Model model) {
		
		List<Genre> genres = (List<Genre>) genreRepository.findAll(); //haetaan tiedot tietokannasta
		model.addAttribute("genres", genres);
		
		return "genrelist"; //genrelist.html
	}
	
	//tyhjä genren lisäyslomake
	
	@GetMapping("/addgenre")
	public String getAddGenreForm(Model model) {
		
		model.addAttribute("genre", new Genre());
		
		return "addgenre"; //addgenre.html
	}
	
	//lisäyslomakkeella syötettyjen tietojen vastaanotto ja tallennus
	
	@PostMapping("/savegenre")
	public String saveGenre(@Valid @ModelAttribute("genre") Genre genre, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "addgenre";
		} else {
			genreRepository.save(genre); //tallennetaan uusi genre tietokantaan, jos id-arvo on 0 tai null
			return "redirect:/genrelist";
		}
	}
	
	//muokkauslomake
	
	@GetMapping("/editgenre/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editGenreForm(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("genre", genreRepository.findById(id));
		
		return "editgenre"; //editgenre.html
	}
	
	//muokkauslomakkeella syötettyjen tietojen vastaanotto ja tallennus
	
	@PostMapping("/updategenre/{id}")
	public String updateGenre(@ModelAttribute Genre genre) {
		
		genreRepository.save(genre);
		
		return "redirect:/genrelist";
	}
	
	//genren poisto id-arvon avulla
	
	@GetMapping("/deletegenre/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGenreById(@PathVariable("id") Long id, Model model) {
		
		genreRepository.deleteById(id);
		
		return "redirect:/genrelist";
	}
}
