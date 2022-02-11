### WebSockets API

The WebSocketClient can be configured using:
- StandardWebSocketClient provided by any JSR-356 implementation like Eclipse Tyrus
- JettyWebSocketClient provided by Jetty 9+ native WebSocket API
- Any implementation of Spring’s WebSocketClient


#### Usage:
- Copy project
- Run App.java
- This app connects to "/topic/wallet" topic and displays messages on console.
- When server is not available waits 2 seconds and tries again until have a connection. Demostrates reconnect functionality.

- http://localhost:8888/index.html