# deserialize-data-comparator
Sample project to demonstrate ways to deserialize and compare data 

### Requirements
* Java 17
* Maven
* Junit 5
* Spring jdbc
* Univocity parser
* Postgresql

### Setup
* Insert mock_data.sql into a local postgres database (pgAdmin)

### Run Configurations
* Complete with host, port, username and password:
`mvn clean test -Djdbc.url=jdbc:postgresql://<host>:<port>/course_data -Duser=<usernmae> -Dpassword=<password>`
