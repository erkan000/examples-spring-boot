package graphql.subscription.ksqldb;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import io.confluent.ksql.api.client.Row;


public class RowSubscriber implements Subscriber<Row> {

	private Subscription subscription;

	@Override
	public synchronized void onSubscribe(Subscription subscription) {
		System.out.println("Subscriber is subscribed.");
		this.subscription = subscription;

		// Request the first row
		subscription.request(1);
	}

	@Override
	public synchronized void onNext(Row row) {
		System.out.println("Received a row!");
		System.out.println("Row: " + row.values());

		// Request the next row
		subscription.request(1);
	}

	@Override
	public synchronized void onError(Throwable t) {
		System.out.println("Received an error: " + t);
	}

	@Override
	public synchronized void onComplete() {
		System.out.println("Query has ended.");
	}
}
