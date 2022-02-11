### WebSockets API

The WebSocketClient can be configured using:
- StandardWebSocketClient provided by any JSR-356 implementation like Eclipse Tyrus
- JettyWebSocketClient provided by Jetty 9+ native WebSocket API
- Any implementation of Spring’s WebSocketClient


#### Usage:
- Copy project
- Run App.java
- This app sends Stomp messages on every 5 seconds to topic called "/topic/wallet"