# Articles RESTful API

RESTful API for managing articles.

## Getting Started
To use this API, follow the instructions below:

- Clone the repository: `git clone https://github.com/ashishsaranshakya/Articles-Collection.git`
- Change directory to `backend`
- Install the necessary dependencies: `npm install`
- Start the server: `node index.js`
- Access the API at `http://localhost:3000/`

## API Endpoints

### Get All Articles
- Endpoint: `/articles`
- Method: GET
- Description: Retrieves a list of all articles.
- Parameters: None

### Get an Article
- Endpoint: `/article/id/{id}` or `/article/title/{title}`
- Method: GET
- Description: Retrieves a specific article by its ID or title.
- Parameters:
   - `{id}`: The ID of the article to retrieve.
   - `{title}`: The title of the article to retrieve.

### Create an Article
- Endpoint: `/article`
- Method: POST
- Description: Creates a new article.
- Parameters:
   - Body: JSON object representing the article data.

### Update an Article
- Endpoint: `/article/id/{id}` or `/article/title/{title}`
- Method: PUT
- Description: Updates an existing article with new data.
- Parameters:
   - `{id}`: The ID of the article to update.
   - `{title}`: The title of the article to update.
   - Body: JSON object representing the updated article data.

### Partially Update an Article
- Endpoint: `/article/id/{id}` or `/article/title/{title}`
- Method: PATCH
- Description: Updates specific fields of an existing article.
- Parameters:
   - `{id}`: The ID of the article to update.
   - `{title}`: The title of the article to update.
   - Body: JSON object representing the fields to be updated.

### Delete an Article
- Endpoint: `/article/id/{id}` or `/article/title/{title}`
- Method: DELETE
- Description: Deletes an existing article.
- Parameters:
   - `{id}`: The ID of the article to delete.
   - `{title}`: The title of the article to delete.

### Delete all Articles
- Endpoint: `/articles`
- Method: DELETE
- Description: Deletes all existing articles.
- Parameters: None

## Error Handling
The API provides appropriate error responses in case of invalid requests or server-side issues. The error responses include appropriate status codes and error messages to assist in troubleshooting.

## Acknowledgements
- Express.js - Fast, unopinionated, minimalist web framework for Node.js.
- Mongoose - Elegant MongoDB object modeling for Node.js.

Please refer to the documentation of the used libraries and frameworks for more details on their usage and configuration.
