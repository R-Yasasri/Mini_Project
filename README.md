## 18001981
# Mini_Project 
 This is the final assignment mini project for the SCS 3203 Middleware course
 
 ## How to build and deploy the API
 
 ### Prerequisites
 JDK 11 or 17
 MySQL 8
 git 
 
 ### Configurations 
 tested only on Windows 10 OS.
 
 ##### JAVA
 Please ensure that you have set up the JAVA_HOME environment variable to your Java installation (ex: C:\Program Files\Java\jdk-11.0.12) and set it in the path environment variable (%JAVA_HOME%\bin). These configurations are required to run the gradle wrapper (gradlew command) to build the project.
 ##### MySQL
 Please create a database named as **petstore** inside your MySQL 8 RDBMS. Change following properties (username,password and port number) inside the application.properties file according to your MySQL 8 installation configurations.
 
quarkus.datasource.username = 'your user name here'
quarkus.datasource.password = 'your password here'
quarkus.datasource.jdbc.url = jdbc:mysql://localhost:'your port number here'/petstore?verifyServerCertificate=false&autoReconnect=true&useSSL=false
 
 
 ### Steps
 
 ##### 1. clone the git repository
 git clone https://github.com/R-Yasasri/Mini_Project.git
 ##### 2. go to the 'petstore-master' directory
 cd petstore-master
 ##### 3. build the project
 gradlew build -Dquarkus.package.type=uber-jar
 ##### 4. run the project
 java -jar build/petstore-runner.jar
 
 Now the project will be run. You can test it by going this URL: 'http://localhost:8080/pets'
 
 
 ## CURL commands with example parameters

##### load all pet types

curl --location --request GET 'http://localhost:8080/pettypes'

##### save a pet type

curl --location --request POST 'http://localhost:8080/pettypes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "petType": "Cat",
    "status": 1
}'

##### edit a pet type

curl --location --request PUT 'http://localhost:8080/pettypes' \
--header 'Content-Type: application/json' \
--data-raw '    {
        "idpetType": 1,
        "petType": "Dog",
        "status": 1
    }'

##### search pet type by pet type id

curl --location --request GET 'http://localhost:8080/pettypes/1'

##### search pet type by pet type name

curl --location --request GET 'http://localhost:8080/pettypes/findbyname/Dog'

##### Delete a pet type

curl --location --request DELETE 'http://localhost:8080/pettypes/1'

##### Load all pets

curl --location --request GET 'http://localhost:8080/pets'

##### Save a pet

curl --location --request POST 'http://localhost:8080/pets' \
--header 'Content-Type: application/json' \
--data-raw '{
    "petAge": 5,
    "petName": "Boola",
    "idpetType":{
        "idpetType": 1,
        "petType": "Dog",
        "status": 1
    }
}'

##### Search a pet by name

curl --location --request GET 'http://localhost:8080/pets/findbyname/Boola'

##### Search a pet by pet id

curl --location --request GET 'http://localhost:8080/pets/1'

##### Update a pet

curl --location --request PUT 'http://localhost:8080/pets' \
--header 'Content-Type: application/json' \
--data-raw '{
        "idpetType": {
            "idpetType": 1,
            "petType": "Dog",
            "status": 1
        },
        "petAge": 5,
        "petId": 1,
        "petName": "Buula"
    }'

##### Delete a pet

curl --location --request DELETE 'http://localhost:8080/pets/1'
