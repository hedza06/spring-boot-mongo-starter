# Spring Boot Starter - Mongo Data

This project is a starter project for Spring Boot Mongo Data. It contains technologies as Spring Boot, MongoDB 
and Mongobee. It's still in the early phase of development.

The first step of development for the next phase will be to implement JWT Token Authorization and to secure sensitive 
routes by setting up Spring Security. 

## Running the project

1. Make sure that your mongo service is running
2. Create database with name **spring_mongo**
3. Optionally change the port (database uri) in yml (dev, prod) files
4. Open terminal and navigate to your project
5. Type command **mvn install**
6. Type command **mvn spring-boot:run**

## Profiles available

- Development profile (dev)
- Production profile (prod)

## Packaging for production

**mvn clean package -Pprod**

## Contribution
If someone is interesting in contribution please contact me on e-mail ```hedzaprog@gmail.com```. This is not the last
project so it will be more interesting things to come for Spring Boot especially.

<br><br>
Written by Heril Muratović.