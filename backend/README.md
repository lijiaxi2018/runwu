# Backend
Web server for RunWu, an accessible platform for sharing art works & ideas.

## Swagger
Graphical User Interface to test APIs provided by the server.

### Docker Run Swagger
1. Use the following commands to run the Swageer Editor
```
docker pull swaggerapi/swagger-editor
docker run -d -p 80:8080 swaggerapi/swagger-editor
```
2. Import swagger.json to the Swageer Editor

## Spring Backend

### Running the Backend in the command line (Mac)
Go to the backend directory
```
cd runwu/backend
```

Install the Maven
```
brew install maven
```

Install dependencies
```
mvn install 
```

Compile
```
mvn package spring-boot:repackage
```

Start the application
```
java -jar target/springboot-hello-1.0-SNAPSHOT.jar
```

### Running the Backend via Docker
```
docker build -t runwu_webapi -f DockerFile .
docker run -p 8081:8081 -td runwu_webapi
```


### Tips on Connect between Backend (Docker) and MongoDB (Local)
Using the following as the Connection String
```
"mongodb://host.docker.internal:27017"
```