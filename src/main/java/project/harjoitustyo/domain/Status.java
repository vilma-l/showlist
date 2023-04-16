package project.harjoitustyo.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Status {
	
	//attribuutit
	@Id //pääavainsarake
	private Long statusid;
	
	@NotNull
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "status") //yhteys Show-luokkaan
	@JsonIgnoreProperties("status") //ikuisen loopin esto
	private List<Show> shows; //lista ohjelmista
	
	//konstruktorit
	public Status(Long statusid, String name) {
		super();
		this.statusid = statusid;
		this.name = name;
	}
	
	public Status() {
		super();
		this.statusid = null;
		this.name = null;
	}
	
	public Status(String name) {
		super();
		this.statusid = null;
		this.name = name;
	}
	
	//getterit & setterit
	public Long getStatusid() {
		return statusid;
	}

	public void setStatusid(Long statusid) {
		this.statusid = statusid;
	}

	public String getName() {
		return name;
	}

	public void setStatus(String name) {
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
		return "Status [statusid=" + statusid + ", name=" + name + "]";
	}

}
