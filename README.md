## 18001981
# Mini_Project 
 This is the final assignment mini project for the SCS 3203 Middleware course
 
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
