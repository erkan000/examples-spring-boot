package springdata;

import static org.assertj.core.api.Assertions.assertThat;

import springdata.pojo.Person;
import springdata.repository.PersonDerivedQueryRepository;

import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DerivedQueryTests {
	
    @Autowired
    private PersonDerivedQueryRepository personRepository;

    @Test
    public void t1() {
        final Person p1 = createPerson("Erkan", "Sar", 36);
        final Person p2 = createPerson("Nurdan", "Sar", 30);
        final Person p3 = createPerson("Erkan", "Test", 54);

        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);

        List<Person> personsByName = personRepository.findByName("Erkan");

        assertThat(personsByName).hasSize(2);
        assertThat(personsByName.get(0)).isEqualToComparingFieldByField(p1);
        assertThat(personsByName.get(1)).isEqualToComparingFieldByField(p3);
    }

    @Test
    public void t2() {
    	final Person p1 = createPerson("Erkan", "Sar", 36);
        final Person p2 = createPerson("Nurdan", "Sar", 30);
        final Person p3 = createPerson("Erkan", "Test", 54);

        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);

        final List<Person> personsByNameAndAge = personRepository.findByNameAndAge("Erkan", 36);

        assertThat(personsByNameAndAge)
            .hasSize(1)
            .first()
            .isEqualToComparingFieldByField(p1);
    }

    @Test
    public void t3() {
    	final Person p1 = createPerson("Erkan", "Sar", 36);
        final Person p2 = createPerson("Nurdan", "Sari", 30);
        final Person p3 = createPerson("Erkan", "Test", 54);

        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);

        final List<Person> personsBySurname = personRepository.findBySurnameIn("Test", "Sari");

        assertThat(personsBySurname).hasSize(2);
        assertThat(personsBySurname.get(0)).isEqualToComparingFieldByField(p2);
        assertThat(personsBySurname.get(1)).isEqualToComparingFieldByField(p3);
    }

    @Test
    public void t4() {
    	final Person p1 = createPerson("Erkan", "Sar", 36);

        personRepository.save(p1);

        final List<Person> personsByName = personRepository.findByNameIgnoreCase("erKaN");

        assertThat(personsByName)
            .hasSize(1)
            .first()
            .isEqualToComparingFieldByField(p1);
    }

	private Person createPerson(String name, String surname, int age) {
		final Person person = new Person();
		person.setName(name);
		person.setSurname(surname);
		person.setAge(age);
		person.setDayOfBirth(LocalDate.parse("1984-11-11"));
		return person;
	}

}

