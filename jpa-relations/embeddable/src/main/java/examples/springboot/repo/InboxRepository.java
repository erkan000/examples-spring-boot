package examples.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examples.springboot.entity.Inbox;
import examples.springboot.entity.pk.Pk;

@Repository
public interface InboxRepository extends JpaRepository<Inbox, Pk>{

	

}
