# READ ME

Sample Rest API to query github and return the custom user summary as expected.

### How to run the service
##### Prerequisites
- java version "17.0.3.1" 2022-04-22 LTS
- Apache Maven 3.8.4

##### Intellij
- Clone the project from git using git clone <link>
- Extract the project
- Intellij > File > New > Project from Existing Sources
- Import as Maven Project
- Run the Main Class (ProjectApplication)

##### Maven
- Clone the project from git using git clone <link>
- Extract the project
- From the project root director run mvn clean install package
- Run java -jar target/<latest-jar-file>

### Architecture
The code is organized in a standard msc (model, service, controller) format so all the relevant details for a particular level are stored together. 
The mappers for converting to models are stored along with the models. This way we could add more mappers in future and keep them bundled together.
Similarly, we could add multiple clients and exceptions and keep them closed knit, under their own respective packages. 

### Decisions
- Caching: EhCache was used as it could be run within the JVM framework and is super lightweight. For a production grade application with heavy load, we could use a separate memcached server. 
- Language / Framework: JAVA & Spring Boot was used because of familiarity. Spring Boot serves as a perfect framework to create stable & scalable production grade Rest microservices.
- Libraries
  - Dozer: Is useful in mapping individual fields of models and also helps in creating custom mappers. If we are creating a generic client or pass through service, we could JSON Simple / Jackson.
  - Rest Template: Rest Template was used to keep things simple as we do not have parallel async calls. Otherwise, we could use HttpClient for making parallel async calls.
- Logging: Used SLF4J to log info & errors for visibility.
- Actuator: To monitor metrics and status of the service.
- Didn't use Docker to bundle up the service given the time constraints.

