package examples.springboot.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examples.springboot.entity.Label;


@Repository
public interface LabelRepository extends JpaRepository<Label, UUID>{

	boolean existsByName(String name);
	
}
