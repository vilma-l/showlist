package project.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import project.harjoitustyo.domain.Genre;
import project.harjoitustyo.domain.GenreRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GenreRepositoryTests {
	
	@Autowired
	GenreRepository genreRepository;
	
	@Test //testataan GenreRepositoryn findByName - metodin toimivuutta
	public void findByNameShouldReturnGenre() {
		
		List<Genre> genres = genreRepository.findByName("Medical drama");
		
		assertThat(genres).hasSize(1);
	}
	
	@Test //testataan GenreRepositoryn save()-metodin toimivuutta
	public void createNewGenre() {
		
		Genre genre = new Genre("Test");
		genreRepository.save(genre);
		
		assertThat(genre.getGenreid()).isNotNull();
	}
	
	@Test //testataan GenreRepositoryn delete()-metodin toimivuutta
	public void deleteGenre() {
		
		Genre genre = genreRepository.findById(Long.valueOf(1)).get();
		genreRepository.delete(genre);
		
		Optional<Genre> deleteGenre = genreRepository.findById(Long.valueOf(1));
		assertThat(deleteGenre).isEmpty();
	}
}
