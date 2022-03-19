package graphql.subscription;

import java.time.Duration;
import java.util.UUID;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import graphql.dto.Person;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import reactor.core.publisher.Flux;

@Component
public class Subscription implements GraphQLSubscriptionResolver {
	
	public Publisher<Person> streamPersonEvent() {
		
		return Flux.interval(Duration.ofSeconds(1))
                .map(num -> new Person(UUID.randomUUID().toString(), 5, "email"));
	}

}
