package project.harjoitustyo.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Genre {
	
	//attribuutit
	@Id //pääavainsarake
	@GeneratedValue(strategy = GenerationType.AUTO) //automaattisesti generoituva id-arvo
	private Long genreid;
	
	@Size(min = 2, max = 30) //syötetyn genren nimen merkkimäärän rajoitteet
	@NotNull
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "genre") //yhteys Show-luokkaan
	@JsonIgnoreProperties("genre") //ikuisen loopin esto
	private List<Show> shows; //lista ohjelmista
	
	//konstruktorit
	public Genre(Long genreid, String name) {
		super();
		this.genreid = genreid;
		this.name = name;
	}
	
	public Genre() {
		super();
		this.genreid = null;
		this.name = null;
	}
	
	public Genre(String name) {
		super();
		this.genreid = null;
		this.name = name;
	}
	
	//getterit & setterit
	
	public Long getGenreid() {
		return genreid;
	}

	public void setGenreid(Long genreid) {
		this.genreid = genreid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}
	
	//toString
	
	@Override
	public String toString() {
		return "Genre [genreid=" + genreid + ", name=" + name + "]";
	}
}
