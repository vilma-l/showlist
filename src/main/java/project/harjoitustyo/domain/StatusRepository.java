package project.harjoitustyo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Long> {
	
	//peritään findAll(), findById(), save(), deleteById()
	
	List<Status> findByName(String name);
	Optional<Status> findById(Long statusid);

}
