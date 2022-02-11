package websocket.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@Configuration
public class Config {

	@Value("${socket.endpoint}")
	private String url;
	
	@Bean
	public WebSocketStompClient webSocketStompClient(WebSocketClient webSocketClient,
			StompSessionHandler stompSessionHandler) {
		WebSocketStompClient webSocketStompClient = new WebSocketStompClient(webSocketClient);
		webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
		webSocketStompClient.connect(url, stompSessionHandler);
		return webSocketStompClient;
	}

	@Bean
	public WebSocketClient webSocketClient() {
		List<Transport> transports = new ArrayList<>();
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
		transports.add(new RestTemplateXhrTransport());
		return new SockJsClient(transports);
//		return new StandardWebSocketClient();
	}

	@Bean
	public StompSessionHandler stompSessionHandler() {
		return new MyStompSessionHandler();
	}

}
