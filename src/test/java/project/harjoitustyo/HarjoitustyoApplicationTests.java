package project.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import project.harjoitustyo.web.GenreController;
import project.harjoitustyo.web.ShowController;
import project.harjoitustyo.web.UserController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HarjoitustyoApplicationTests {
	
	@Autowired
	private ShowController showController;
	
	@Autowired
	private GenreController genreController;
	
	@Autowired
	private UserController userController;

	@Test
	public void contextLoads() throws Exception {
		
		assertThat(showController).isNotNull();
		assertThat(genreController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
