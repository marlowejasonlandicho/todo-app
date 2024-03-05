# Synopsis

To Do Spring Boot App

# Services available

- **Create To Do Item** : Create a To Do item

- **List All To Do Items** : Retrieves all To Do items

- **Get To Do Item** : Retrieves a single To Do item

- **Update To Do Item** : Updates a single To Do item

- **Complete To Do Item** : Completes a single To Do item

- **Delete To Do Item** : Deletes a single To Do item

# Installation

Make sure that the following are installed on your machine:

- **Java 17**
- **Maven**

# Testing

There's an existing Unit Test class file named  ```src/test/java/exam/todo/TodoServiceApplicationTests.java``` that you can run the test against the Spring Boot App


# Running the application

  - Maven is required to build OR run the Spring Boot application, Spring Boot application runs on the default port, 8080
  - To build the project, run ```mvn install``` on the project root folder
  - To run the project, either run by ```mvn spring-boot:run``` on the project root folder, OR
  - Run ```java -jar ./target/todo-service-1.0.0.jar``` on the project root folder

# Generic Response message

The Service end-points have a generic response structure contained in a single JSON Object. 
The generic response contains the following attributes:

 - **apiStatus** : To Do Spring Boot App specific verbose status. Please ApiStatus.java file for more details
 - **message** : Human readable API Response message 
 - **success** : Successful or Failed request boolean indicator
 - **data** : The Service specific response for the given request. JSON Object response varies per Service.
 
**Sample Response from Service:**

```json
{
    "apiStatus": "CREATED",
    "message": "Todo Item created",
    "success": true,
    "data": <<Could be JSON Object or JSON Array>>
}
```

# Service end-points available

## Create To Do Item

Create a To Do item

**URL** : `/todo`

**Method** : `POST`

**Request BODY attributes** :

- **name** : Name of To Do Item to be saved in DB

**Response BODY data attributes** :

- **todoId** : An Integer value indicating Unique ID of the To Do Item saved in DB
- **name** : Name of To Do Item saved in DB
- **status** : Status of To Do Item saved in DB. It could be of CREATED - 0, IN_PROGRESS - 1, or COMPLETED - 2

**Request example:**
    
```POST /todo```
```json
{
    "name": "To Do Item"
}
```

**Response example** :

```json
{
    "apiStatus": "CREATED",
    "message": "Todo Item created",
    "success": true,
    "data": {
        "todoId": 1,
        "name": "To Do Item",
        "status": 0
    }
}
```

## List All To Do Items

Retrieves all To Do items

**URL** : `/todo`

**Method** : `GET`

**Response BODY data attributes** :

List of To DO Items containing the ff attributes
- **todoId** : An Integer value indicating Unique ID of the To Do Item saved in DB
- **name** : Name of To Do Item saved in DB
- **status** : Status of To Do Item saved in DB. It could be of CREATED - 0, IN_PROGRESS - 1, or COMPLETED - 2

**Request example** :

```GET /todo```

**Response example** :

```json
{
    "apiStatus": "FOUND",
    "message": "Todo Item found",
    "success": true,
    "data": [
        {
            "todoId": 1,
            "name": "New Todo Item 2",
            "status": 0
        },
        {
            "todoId": 2,
            "name": "New Todo Item 1",
            "status": 0
        }
    ]
}
```

## Get To Do Item

 Retrieves a single To Do item
 
**URL** : `/todo/{todoId}`

**Method** : `GET`

**Request Path Variable** :

- **todoId** : To Do Item Id from DB


**Response BODY data attributes** :

- **todoId** : An Integer value indicating Unique ID of the To Do Item saved in DB
- **name** : Name of To Do Item saved in DB
- **status** : Status of To Do Item saved in DB. It could be of CREATED - 0, IN_PROGRESS - 1, or COMPLETED - 2


**Request example** :

```GET /todo/1```

**Response example** :

```json
{
    "apiStatus": "FOUND",
    "message": "Todo Item found",
    "success": true,
    "data": {
        "todoId": 1,
        "name": "New Todo 1",
        "status": 0
    }
}
```

## Update To Do Item

 Updates a single To Do item 
 
**URL** : `/todo/{todoId}`

**Method** : `PUT`

**Request Path Variable** :

- **todoId** : To Do Item Id from DB

**Request BODY attributes** :

- **name** : Name of To Do Item to be saved in DB, or
- **status** : Status of To Do Item to be saved in DB. It could be of CREATED - 0, IN_PROGRESS - 1, or COMPLETED - 2

**Response BODY data attributes** :

- **todoId** : An Integer value indicating Unique ID of the To Do Item saved in DB
- **name** : Name of To Do Item saved in DB
- **status** : Status of To Do Item saved in DB. It could be of CREATED - 0, IN_PROGRESS - 1, or COMPLETED - 2


**Request example** :

```PUT /todo/1```
```json
{
    "name": "Updated Name",
    "status": 1
}
```
**Response example**

```json
{
    "apiStatus": "UPDATED",
    "message": "Todo Item updated",
    "success": true,
    "data": {
        "todoId": 1,
        "name": "Updated Name",
        "status": 1
    }
}
```

## Complete To Do Item

 Completes a single To Do item
 
**URL** : `/todo/complete/{todoId}`

**Method** : `PUT`

**Request Path Variable** :

- **todoId** : To Do Item Id from DB


**Response BODY data attributes** :

- **todoId** : An Integer value indicating Unique ID of the To Do Item saved in DB
- **name** : Name of To Do Item saved in DB
- **status** : Status of To Do Item saved in DB. It could be of CREATED - 0, IN_PROGRESS - 1, or COMPLETED - 2


**Request example** :

```PUT /todo/complete/1```

**Response example** :

```json
{
    "apiStatus": "COMPLETED",
    "message": "Todo Item completed",
    "success": true,
    "data": {
        "todoId": 1,
        "name": "To Do Item 1",
        "status": 2
    }
}
```

## Delete To Do Item

 Deletes a single To Do item
 
**URL** : `/todo/{todoId}`

**Method** : `DELETE`

**Request Path Variable** :

- **todoId** : To Do Item Id from DB


**Response BODY data attributes** :

- **status** : Boolean value indicating a successful deletion of To Do Item


**Request example** :

```DELETE /todo/1```

**Response example** :

```json
{
    "apiStatus": "DELETED",
    "message": "Todo Item deleted",
    "success": true,
    "data": true
}
```
