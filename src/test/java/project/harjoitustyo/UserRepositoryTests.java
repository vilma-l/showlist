package project.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import project.harjoitustyo.domain.User;
import project.harjoitustyo.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTests {
	
	@Autowired
	UserRepository userRepository;
	
	@Test //testataan UserRepositoryn findByRole - metodin toimivuutta
	public void findByRoleShouldReturnUser() {
		
		List<User> users = userRepository.findByRole("ADMIN");
		
		assertThat(users).hasSize(1);
	}
	
	@Test //testataan UserRepositoryn save()-metodin toimivuutta
	public void createNewUser() {
		
		User user = new User("test", "$2a$10$UJdbF4LJ4CL9aWLvyC1nRem8tZbpCT/Z3TvDGZvZukNh.pvqkkq16", "USER");
		userRepository.save(user);
		
		assertThat(user.getUserid()).isNotNull();
	}
	
	@Test //testataan UserRepositoryn delete()-metodin toimivuutta
	public void deleteUser() {
		
		User user = userRepository.findById(Long.valueOf(1)).get();
		userRepository.delete(user);
		
		Optional<User> deleteUser = userRepository.findById(Long.valueOf(1));
		assertThat(deleteUser).isEmpty();
	}

}
