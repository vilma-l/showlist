package project.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import project.harjoitustyo.domain.Show;
import project.harjoitustyo.domain.ShowRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ShowRepositoryTests {
	
	@Autowired
	ShowRepository showRepository;
	
	@Test //testataan ShowRepositoryn findByTitle - metodin toimivuutta
	public void findByTitleShouldReturnShow() {
		
		List<Show> shows = showRepository.findByTitle("The Good Doctor");
		
		assertThat(shows).hasSize(1);
		assertThat(shows.get(0).getSeason()).isEqualTo(1);
	}
	
	@Test //testataan ShowRepositoryn save()-metodin toimivuutta
	public void createNewShow() {
		
		Show show = new Show("Test", 1, null, null);
		showRepository.save(show);
		
		assertThat(show.getId()).isNotNull();
	}
	
	@Test //testataan ShowRepositoryn delete()-metodin toimivuutta
	public void deleteShow() {
		
		Show show = showRepository.findById(Long.valueOf(1)).get();
		showRepository.delete(show);
		
		Optional<Show> deleteShow = showRepository.findById(Long.valueOf(1));
		assertThat(deleteShow).isEmpty();
	}
}
