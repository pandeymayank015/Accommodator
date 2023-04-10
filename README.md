
# ACCOMMODATOR

Accommodator is a platform that provides a seamless and intuitive user experience for students searching for housing options. With our user-friendly interface, students can search for housing options based on various criteria, including location, price range, and other specific preferences such as roommate preferences. Our software leverages algorithms to efficiently search and display results to students, ensuring a quick and effortless process.



## Tech Stack
Accommodator is built using the following technologies:



**React** 

**Springboot**

**MySQL**

## Getting Started
To run Accommodator on virtual machine, follow these steps:

1. Connect to Dalhousie VPN
2. Frontend URL :  http://csci5308vm25.research.cs.dal.ca:3000 
3. Backend URL : http://csci5308vm25.research.cs.dal.ca:8080
## Installation


1. Clone the frontend branch JWT_Frontend_Integration:

```bash
  git clone -b JWT_Frontend_Integration --single-branch <remote-repo-url>
```
2. Clone the backend branch JWT_Backend_Integration:

```bash
  git clone -b JWT_Backend_Integration --single-branch <remote-repo-url>
```
3. Set up the database:

Create a new MySQL database called accommodator

Update the application.properties file in the src/main/resources directory with your MySQL credentials:
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/accommodator
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

```
4. Start the backend server:
```bash
mvn clean install
```
5. Start the frontend server:
```bash
npm start
```
6. Navigate to http://localhost:3000 in your web browser to access the Accommodator app.


## Dependencies

Add the following dependencies:

1. Spring Data JPA
2. Spring Boot Starter Security
3. Spring Boot Starter Web
4. MySQL Connector Java
5. JJWT :: API
6. JJWT :: Impl
7. JJWT :: Extensions :: Jackson
8. Spring Boot Starter Test
9. Spring Security Test
10. Lombok

## Features

- Registration/Login
- Roommate Matching
- Add posting
- Show posting
- Add to favorites
- Show favorites
- Filtering/ Search
- Connect via Email
- Map API
- Admin approval


## Contributing

Contributions are always welcome!

1. Fork the repository.
2. Create a new branch:
```bash
  git checkout -b new-feature
```
3. Make your changes and commit them:
```bash
  git commit -m "Add new feature"
```
4. Push to the new branch:
```bash
git push origin new-feature
```
5. Create a pull request on GitHub.




## Authors

- [fenilp](https://git.cs.dal.ca/fenilp)
- [mpandey](https://git.cs.dal.ca/mpandey)
- [marni](https://git.cs.dal.ca/marni)
- [sdoshi](https://git.cs.dal.ca/sdoshi)
- [gor](https://git.cs.dal.ca/mpandey)
