package websocket.conf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import websocket.dto.WalletDto;

import java.lang.reflect.Type;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private Logger logger = LogManager.getLogger(MyStompSessionHandler.class);
    
    @Value("${socket.endpoint}")
	private String url;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private MyStompSessionHandler handler;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established : " + session.getSessionId());
        session.subscribe("/topic/wallet", this);
        logger.info("Subscribed to topic");
    }
    
    @Override
	public void handleTransportError(StompSession session, Throwable exception) {
    	logger.error("Transport error!");
    	try {
			Thread.sleep(2000); // wait and try to reconnect!
			WebSocketStompClient client = context.getBean(WebSocketStompClient.class);
			client.connect(url, handler);
		} catch (Exception e) {
			logger.fatal("ERROR: " + e);
		}    	
	}
    
    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
    	logger.error("Exception!", exception);
    }
    
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
    	WalletDto msg = (WalletDto) payload;
    	logger.info("Received : " + msg.getAddress() + " from : " + msg.getName());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return WalletDto.class;
    }

}
