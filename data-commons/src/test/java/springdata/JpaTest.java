package springdata;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import springdata.pojo.Person;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaTest {

	@Autowired
	private EntityManager entityManager;

	@Test
	public void verifyFlighTCanBeSaved() {
		final Person person =  new Person();
		person.setName("Erkan");

		entityManager.persist(person);

		final List<Person> persons = entityManager
				.createQuery("SELECT p FROM Person p", Person.class)
				.getResultList();

		assertThat(persons)
			.hasSize(1)
			.first()
			.isEqualTo(person);
	}

}

