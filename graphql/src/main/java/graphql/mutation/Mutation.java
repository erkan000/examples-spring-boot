package graphql.mutation;

import org.springframework.stereotype.Component;

import graphql.dto.Person;
import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class Mutation implements GraphQLMutationResolver {
	
	public Person createPerson(String name, int age, String email) {
		Person p = new Person(name, age, email);
		System.out.println("Person created!");
		return p;
	}

}
