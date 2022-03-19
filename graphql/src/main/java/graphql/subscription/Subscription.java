package graphql.subscription;

import java.net.MalformedURLException;
import java.net.URL;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import graphql.dto.Person;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

	public Publisher<Person> streamPersonEvent() throws MalformedURLException {

		URL endpoint = new URL("http://localhost:8088");
		final ClientOptions options = ClientOptions.create()
				.setHost(endpoint.getHost())
				.setPort(endpoint.getPort());

		logger.info("Subscribe request arrived");

		final ReactorClient reactorClient = ReactorClient.from(Client.create(options));

		return reactorClient
				.streamQuery("SELECT * from ServiceCategorySourceTAble2 emit changes;")
				.map(row -> {
					return new Person(row.getValue(1).toString(), 3, row.getValue(2).toString());
				});
		
		//		Client client = Client.create(options);
		//		CompletableFuture<StreamedQueryResult> stream = client.streamQuery("SELECT * from LeftyProviderServiceViewTAble emit changes;");
		//		Publisher<Row> result = FlowAdapters.toFlowPublisher(stream.get());		
		//		stream.thenAccept(streamedQueryResult -> {
		//			System.out.println("Query has started. Query ID: " + streamedQueryResult.queryID());
		//			RowSubscriber subscriber = new RowSubscriber();
		//			streamedQueryResult.subscribe(subscriber);
		//		}).exceptionally(e -> {
		//			System.out.println("Request failed: " + e);
		//			return null;
		//		});
	}

	
	private Logger logger = LoggerFactory.getLogger(Subscription.class);

}
