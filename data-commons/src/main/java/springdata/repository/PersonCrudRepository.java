package springdata.repository; 

import org.springframework.data.repository.CrudRepository;

import springdata.pojo.Person;

public interface PersonCrudRepository extends CrudRepository<Person, Integer> {

    // No need to write CRUD Operations !!!
    
}
