package project.harjoitustyo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import project.harjoitustyo.domain.GenreRepository;
import project.harjoitustyo.domain.Show;
import project.harjoitustyo.domain.ShowRepository;
import project.harjoitustyo.domain.StatusRepository;

@Controller
public class ShowController {
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	//listausnäkymä
	
	@GetMapping("/showlist")
	public String getShows(Model model) {
		
		List<Show> shows = (List<Show>) showRepository.findAll(); //haetaan tiedot tietokannasta
		model.addAttribute("shows", shows);
		
		return "showlist"; //showlist.html
	}
	
	//tyhjä lisäyslomake
	
	@GetMapping("/addshow")
	public String getAddShowForm(Model model) {
		
		model.addAttribute("show", new Show());
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("statuses", statusRepository.findAll());
		
		return "addshow"; //addshow.html
	}
	
	//lisäyslomakkeella syötettyjen tietojen vastaanotto ja tallennus
	
	@PostMapping("/saveshow")
	public String saveShow(@Valid @ModelAttribute("show") Show show, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "addshow";
		} else {
			showRepository.save(show); //tallennetaan uusi sarja tietokantaan, jos id-arvo on 0 tai null
			return "redirect:/showlist";
		}
	}
	
	//muokkauslomake
	
	@GetMapping("/editshow/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editShowForm(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("show", showRepository.findById(id));
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("statuses", statusRepository.findAll());
		
		return "editshow"; //editshow.html
	}
	
	//muokkauslomakkeella syötettyjen tietojen vastaanotto ja tallennus
	
	@PostMapping("/updateshow/{id}")
	public String updateShow(@ModelAttribute Show show) {
		
		showRepository.save(show);
		
		return "redirect:/showlist";
	}
	
	//ohjelman poisto id-arvon avulla
	
	@GetMapping("/deleteshow/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteShowById(@PathVariable("id") Long id, Model model) {
		
		showRepository.deleteById(id);
		
		return "redirect:/showlist";
	}
}
