package examples.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import examples.springboot.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String>{


}
