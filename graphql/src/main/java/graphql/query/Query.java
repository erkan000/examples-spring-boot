package graphql.query;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import graphql.dto.Address;
import graphql.dto.Person;
import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class Query implements GraphQLQueryResolver  {	

    public List<Person> getPersons() {
    	Person p1 = new Person(UUID.randomUUID().toString(), 37, "erkan@mail.com");    	
    	Person p2 = new Person(UUID.randomUUID().toString(), 13, "test@mail.com");

    	return List.of(p1, p2);
    }
    
    public Person getPersonByAge(int age, Address adres) {
    	Person p;
    	if(age > 30) {
    		p = new Person(UUID.randomUUID().toString(), age, "erkan@mail.com");
    	}else {
    		p = new Person(UUID.randomUUID().toString(), age, "test@mail.com");
    	}
    	return p;
    }
}