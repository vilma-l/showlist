package project.harjoitustyo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Show {
	
	//attribuutit
	@Id //pääavainsarake
	@GeneratedValue(strategy = GenerationType.AUTO) //automaattisesti generoituva id-arvo
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 50) //syötetyn ohjelman nimen merkkimäärän rajoitteet
	private String title;
	
	@NotNull
	private int season;
	
	@ManyToOne() //yhteys Genre-luokkaan
	@JsonIgnoreProperties("shows") //ikuisen loopin esto
	@JoinColumn(name = "genreid") //viiteavain
	@NotNull
	private Genre genre;
	
	@ManyToOne() //yhteys Status-luokkaan
	@JsonIgnoreProperties("shows") //ikuisen loopin esto
	@JoinColumn(name = "statusid") //viiteavain
	@NotNull
	private Status status;
	
	//konstruktorit
	public Show(Long id, String title, int season, Genre genre, Status status) {
		super();
		this.id = id;
		this.title = title;
		this.season = season;
		this.genre = genre;
		this.status = status;
	}
	
	public Show() {
		super();
		this.id = null;
		this.title = null;
		this.season = 0;
		this.genre = null;
		this.status = null;
	}
	
	public Show(String title, int season, Genre genre, Status status) {
		super();
		this.id = null;
		this.title = title;
		this.season = season;
		this.genre = genre;
		this.status = status;
	}
	
	//getterit & setterit
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}
	
	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	//toString
	@Override
	public String toString() {
		return "Show [id=" + id + ", title=" + title + ", season=" + season + ", genre=" + genre + ", status=" + status
				+ "]";
	}
}
