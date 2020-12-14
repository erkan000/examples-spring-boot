### Spring Boot
Spring Boot Hello World


#### Usage:
- Copy project
- Add environment variable from "Run Configurations", called DB_USER
- Run App.java
- Navigate http://localhost:5000/api


#### Docker
- mvn clean package
- docker build -t myapp -f Dockerfile .
- docker run -p 5000:5000 myapp

