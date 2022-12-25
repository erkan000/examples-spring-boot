package examples.springboot.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examples.springboot.entity.Technology;


@Repository
public interface TechnologyRepository extends JpaRepository<Technology, UUID>{


	
}
