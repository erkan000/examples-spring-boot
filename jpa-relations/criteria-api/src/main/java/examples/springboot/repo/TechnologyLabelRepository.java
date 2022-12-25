package examples.springboot.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import examples.springboot.entity.TechnologyLabel;


@Repository
public interface TechnologyLabelRepository extends JpaRepository<TechnologyLabel, UUID>, JpaSpecificationExecutor<TechnologyLabel>{


	
}
