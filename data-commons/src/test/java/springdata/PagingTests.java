package springdata;

import static org.assertj.core.api.Assertions.assertThat;

import springdata.pojo.Person;
import springdata.repository.PersonPagingSortingRepository;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@DataJpaTest
public class PagingTests {
	
    @Autowired
    private PersonPagingSortingRepository personRepository;

    @Test
    public void t1() {
        personRepository.save(createPerson("Erkan", "x"));
        personRepository.save(createPerson("test", "y"));
        personRepository.save(createPerson("Dene", "z"));
        for (int i = 0; i < 10; i++) {
        	personRepository.save(createPerson("Erkan", "x"+i));
		}

        Pageable pageRequest = PageRequest.of(0, 5); // limit 5
		Page<Person> personsByName = personRepository.findByName("Erkan", pageRequest);
        assertThat(personsByName.getTotalElements()).isEqualTo(11);
        assertThat(personsByName.getNumberOfElements()).isEqualTo(5);
        assertThat(personsByName.getTotalPages()).isEqualTo(3);
        assertThat(personsByName.getContent())
        	.extracting(Person::getSurname)
        	.containsExactly("x","x0","x1","x2","x3");
        
        Page<Person> personsByName2 = personRepository.findByName("Erkan", PageRequest.of(1, 4, Sort.by(Direction.DESC, "name"))); // limit 4 offset 1
        assertThat(personsByName2).hasSize(4);
        
        Page<Person> personsByName3 = personRepository.findByName("Erkan", PageRequest.of(2, 5)); // limit 5 offset 2
        assertThat(personsByName3).hasSize(1);
        
        Iterable<Person> personsByName4 = personRepository.findAll(Sort.by(Direction.DESC, "surname"));
        assertThat(personsByName4).hasSize(13);
        for (Person person : personsByName4) {
			System.out.println(person.getSurname());
		}
    }

    

	private Person createPerson(String name, String surname) {
		final Person person = new Person();
		person.setName(name);
		person.setSurname(surname);
		person.setAge(36);
		person.setDayOfBirth(LocalDate.parse("1984-11-11"));
		return person;
	}

}

