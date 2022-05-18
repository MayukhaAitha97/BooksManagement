# BooksManagement

## Description

Well-structured project to manage books in a library.

Attributes of the book:

* ISBN
* Title
* Author
* Price
* Genre


 
Functional Features:

* CRUD interface to access the books by ISBN
* Search API by any of the attributes of the books
* Rest API to Import books from CSV

 
Technical Features:

* DB Storage (in memory approach with H2 database)
* Swagger on the REST API


## Getting Started
Given below are the instructions on how to run this project
### Dependencies

* Eclipse or any other IDE
* Java 18
* STS installed in IDE

### Installing

* Click on Code under the repository 
* Clone or download the project as zip

### Executing program

* Import the downloaded unzipped project as an existing maven project into your IDE
* Right Click on the project, go to build path, go to configure build path and check if the java version in the library is 18
* If it's not 18, download Java 18 version and in configure build path use the option Alternate JRE and use Java 18 
* To run this project, navigate to SpringbootbooksapiApplication.java file, right click on the file and run as Spring Boot App
* In your console you will see Started SpringbootbooksapiApplication
* Now, you can test the project on postman or any other testing tools 
* You can open the swagger at http://localhost:8092/swagger-ui.html and test with Swagger


## Author

Sai Mayukha Aitha
email: mayukha.sai97@gmail.com


## Version History
* 0.1
    * Initial Release
