# Spring Boot Starter - Mongo Data

This is a starter project for Spring Boot Mongo Data. It contains technologies as Spring Boot, MongoDB, Mongobee
and Spring Security with JWT Token Authorization. Project is still in early phase of development but it's a good
point to start learning Spring Boot with MongoDB.

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

## Authorization

Authentication route: **/auth/login**  
Method: **POST**  
Content-Type: **application/json**  
Request body: `{ username: "admin", password: "admin123", rememberMe: true/false }`  
Response: `{ token: "token_hash" }`  


## Packaging for production

1. **mvn clean**  
2. **mvn -Pprod package**

## Contribution

If someone is interesting in contribution please contact me on e-mail ```hedzaprog@gmail.com```. This is not the last
project so it will be more interesting things to come for Spring Boot especially.

## Author

Heril Muratović   
Software Engineer  
<br>
**Mobile**: +38269657962  
**E-mail**: hedzaprog@gmail.com  
**Skype**: hedza06  
**Twitter**: hedzakirk  
**LinkedIn**: https://www.linkedin.com/in/heril-muratovi%C4%87-021097132/  
**StackOverflow**: https://stackoverflow.com/users/4078505/heril-muratovic
