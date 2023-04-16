package project.harjoitustyo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
	
	//peritään findAll(), findById(), save(), deleteById()
	
	List<Genre> findByName(String name);
	Optional<Genre> findById(Long genreid);

}
