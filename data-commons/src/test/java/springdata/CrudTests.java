package springdata;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springdata.pojo.Person;
import springdata.repository.PersonCrudRepository;

@DataJpaTest
public class CrudTests {

	@Autowired
	private PersonCrudRepository personRepository;

	// @DataJpaTest methods doesnt commit changes to DB!

	@Test
	public void crudTests() {
		final Person refPerson = createPerson();
		
		Person savedPerson = personRepository.save(refPerson);
		assertThat(refPerson)
			.usingRecursiveComparison()
			.isEqualTo(savedPerson);

		Iterable<Person> savedPersons = personRepository.findAll();
		assertThat(savedPersons)
			.hasSize(1)
			.first()
			.usingRecursiveComparison()
			.isEqualTo(refPerson);

		Optional<Person> findPerson = personRepository.findById(savedPerson.getId());
		assertThat(findPerson.get())
			.isNotNull()
			.usingRecursiveComparison()
			.isEqualTo(refPerson);

		personRepository.deleteById(savedPerson.getId());
		assertThat(personRepository.count())
			.isZero();
	}

	private Person createPerson() {
		final Person person = new Person();
		person.setName("Erkan");
		person.setSurname("Sar");
		person.setAge(36);
		person.setDayOfBirth(LocalDate.parse("1984-11-11"));
		return person;
	}

}

