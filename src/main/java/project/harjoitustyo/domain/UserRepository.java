package project.harjoitustyo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username); //sisäänkirjautumistestiä varten
	List<User> findByRole(String role);
	Optional<User> findById(Long userid);

}
