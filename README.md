## 18001981
# Mini_Project 
 This is the final assignment mini project for the SCS 3203 Middleware course
 
 ## How to build and deploy the API
 
 ### Prerequisites
 JDK 11 or 17
 MySQL 8
 git 
 
 ### Configurations
 ##### JAVA
 ##### MySQL
 
 ### Steps (tested with Windows OS)
 
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
