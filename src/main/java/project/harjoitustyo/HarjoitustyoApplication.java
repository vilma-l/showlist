package project.harjoitustyo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import project.harjoitustyo.domain.Genre;
import project.harjoitustyo.domain.GenreRepository;
import project.harjoitustyo.domain.Show;
import project.harjoitustyo.domain.ShowRepository;
import project.harjoitustyo.domain.Status;
import project.harjoitustyo.domain.StatusRepository;
import project.harjoitustyo.domain.User;
import project.harjoitustyo.domain.UserRepository;

@SpringBootApplication
public class HarjoitustyoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demoData(ShowRepository showRepository, GenreRepository genreRepository, StatusRepository statusRepository, UserRepository userRepository) {
		return (arg) -> {
			
			Status status1 = new Status(1L, "Finished");
			statusRepository.save(status1);
			Status status2 = new Status(2L, "Watching");
			statusRepository.save(status2);
			
			Genre genre1 = new Genre("Medical drama");
			genreRepository.save(genre1);
			Genre genre2 = new Genre("Reality TV");
			genreRepository.save(genre2);
			
			showRepository.save(new Show("The Good Doctor", 1, genreRepository.findByName("Medical drama").get(0), statusRepository.findByName("Finished").get(0)));
			showRepository.save(new Show("RuPaul's Drag Race", 15, genreRepository.findByName("Reality TV").get(0), statusRepository.findByName("Watching").get(0)));
			
			//luodaan käyttäjät: admin/admin ja user/user
			
			User user1 = new User("admin", "$2a$10$3esVYOZOEbfnXXq/1qdkL.swwqsDox9CzpdMGAS.y2.9RJ8jaHEyS", "ADMIN");
			userRepository.save(user1);
			User user2 = new User("user", "$2a$10$5BsEs9MmSpHCT1cn1SFKw.gfUkk/nhVUW49GrM9jJ2SxJygqxvlq2", "USER");
			userRepository.save(user2);
		
		};
	}

}
