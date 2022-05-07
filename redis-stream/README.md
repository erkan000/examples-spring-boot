# Redis Streams Demo

### Setup

- Issue a "docker-compose up -d" command at this folder
- Run the App.java file
- It will try to create a stream named "test-stream", at the begginnig of the app with these commands;

 - docker-compose exec redis sh
 - redis-cli
 - XADD test-stream * any-key any-value
 - XGROUP CREATE test-stream test-stream $

### To Publish a message to redis stream
- http://localhost:8080/send/hey
- You can inspect from "Redis Commander"

### Redis Cli
- You can send redis commands from CLI. 
- Issue a "redis-cli" command from image
- I.e. send a data. "XADD test-stream * any-key3 any-value2"

### Redis Commander
- Redis-commander is a client for Redis. Navigate to http://localhost:8081/ for web interface.


