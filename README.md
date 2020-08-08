# SpringBoot-RESTfulApi-Practice

This project is a REST API I made to learn the Spring Boot framework. It fetches a random food suggestion based on a 
supplied JSON. There is also an endpoint to save a food item to the repository.

## Endpoint: /api/randomfood
Request Type: POST

Description: Retrieves random food based on given tags.

Example: { "foodTags": ["Chicken", "Local"] }

Response Code 200 - OK: Everything worked as expected. Random food is returned in JSON. 
Returns { id: 1, foodName: "Chicken Rice", tags: "Chicken,Asian,Chinese,Local" }
    
Response Code 400 - BAD REQUEST: Invalid JSON passed.

Response Code 404 - NOT FOUND: No food found with the given tags.

## Endpoint: /api/save/food
Request Type: POST

Description: Saves a new food into the repository.

Example: { "foodName": "Chicken Rice", "tags": "Chicken,Asian,Chinese,Local" }

Response Code 200 - OK: Everything worked as expected. Food is saved into repository.
Returns "Food successfully saved to repository!"

Response Code 400 - BAD REQUEST: Invalid JSON passed.