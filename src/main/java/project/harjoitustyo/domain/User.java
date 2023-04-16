package project.harjoitustyo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid", nullable = false, updatable = false)
	private Long userid;
	
	//uniikki käyttäjätunnus
	@Column(name = "username", nullable = false, unique = true)
	@NotNull
	private String username;
	
	@Column(name = "password", nullable = false)
	@NotNull
	private String passwordHash;
	
	@Column(name = "role", nullable = false)
	@NotNull
	private String role;
	
	//konstruktorit

	public User(Long userid, String username, String passwordHash, String role) {
		super();
		this.userid = userid;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	public User(String username, String passwordHash, String role) {
		super();
		this.userid = null;
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}
	
	public User() {
		super();
		this.userid = null;
		this.username = null;
		this.passwordHash = null;
		this.role = null;
	}
	
	//getterit & setterit
	
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	//toString
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", passwordHash=" + passwordHash + ", role=" + role
				+ "]";
	}
}
