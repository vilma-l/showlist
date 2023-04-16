package project.harjoitustyo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import project.harjoitustyo.domain.Status;
import project.harjoitustyo.domain.StatusRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StatusRepositoryTests {
	
	@Autowired
	StatusRepository statusRepository;
	
	@Test //testataan StatusRepositoryn findByName - metodin toimivuutta
	public void findByNameShouldReturnStatus() {
		
		List<Status> statuses = statusRepository.findByName("Finished");
		
		assertThat(statuses).hasSize(1);
	}
	
	@Test //testataan StatusRepositoryn save()-metodin toimivuutta
	public void createNewStatus() {
		
		Status status = new Status(3L, "Test");
		statusRepository.save(status);
		
		assertThat(status.getStatusid()).isNotNull();
	}
	
	@Test //testataan StatusRepositoryn delete()-metodin toimivuutta
	public void deleteStatus() {
		
		Status status = statusRepository.findById(Long.valueOf(1)).get();
		statusRepository.delete(status);
		
		Optional<Status> deleteStatus = statusRepository.findById(Long.valueOf(1));
		assertThat(deleteStatus).isEmpty();
	}

}
