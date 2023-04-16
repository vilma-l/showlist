package project.harjoitustyo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long> {
	
	//peritään findAll(), findById(), save(), deleteById()
	
	List<Show> findByTitle(String title);
	Optional<Show> findById(Long id);

}
