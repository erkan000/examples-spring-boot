package springdata.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import springdata.pojo.Person;

public interface PersonDerivedQueryRepository extends CrudRepository<Person, Integer> {

    List<Person> findByName(String name);

    List<Person> findByNameAndAge(String name, int age);

    List<Person> findBySurnameIn(String ... surnames);

    List<Person> findByNameIgnoreCase(String name);

}
