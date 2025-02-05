# Simple E-Commerce Website API

## Project Description
The **Simple E-Commerce Website API** is a backend service built using the **Spring Boot framework**. It provides RESTful APIs for managing an e-commerce platform's product inventory. The system supports essential product operations such as creating, reading, updating, and deleting (CRUD) products, along with image upload functionality. This API also includes a powerful search feature for querying products based on keywords in the product name or description.

This backend service can be integrated with any front-end framework for creating a full-stack e-commerce application.

---
## Features

- **Product Management:** Create, read, update, and delete products.
- **Image Upload:** Upload product images and store metadata.
- **Product Search:** Search products by keywords in the name or description.
- **Cross-Origin Resource Sharing (CORS):** Support for cross-origin requests.
- **Database Integration:** Uses JPA and Hibernate for database operations.

---
## Technologies Used

- **Backend Framework:** Spring Boot
- **Database:** H2 (for testing), MySQL/PostgreSQL (for production)
- **ORM:** Hibernate
- **API Documentation:** Postman
- **Dependencies:**
  - Spring Web
  - Spring Data JPA
  - Jakarta Persistence API (JPA)
  - Multipart file handling

---
## Endpoints

### Product Management
| HTTP Method | Endpoint                 | Description                       |
|-------------|---------------------------|-----------------------------------|
| GET         | `/api/products`           | Get all products                 |
| GET         | `/api/products/{id}`      | Get product by ID                |
| POST        | `/api/products`           | Create a new product             |
| PUT         | `/api/products/{id}`      | Update an existing product       |
| DELETE      | `/api/products/{id}`      | Delete a product                 |
| GET         | `/api/products/search`    | Search for products by keyword   |

---
## Installation and Setup

### Prerequisites
- Java 17 or later
- Maven
- MySQL/PostgreSQL (optional for production setup)

### Steps to Run Locally
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/simple-ecommerce-api.git
   ```
2. Navigate to the project directory:
   ```bash
   cd simple-ecommerce-api
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### Configuration
Modify the database configuration in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---
## How to Use the API

### Testing with Postman
Import the API collection into Postman and use the endpoints for product management and search operations.

1. **Product Creation Example:**
   - **Endpoint:** `POST /api/products`
   - **Body:** Form-data with fields:
     - `productName`: "Sample Product"
     - `description`: "Description here"
     - `price`: 100.0
     - `productCategory`: 1
     - `quantity`: 10
     - `available`: true
     - `image`: file upload (PNG or JPG)

2. **Search Example:**
   - **Endpoint:** `GET /api/products/search?keyword=laptop`

### Response Example
```json
[
  {
    "id": 1,
    "productName": "Laptop Pro 15",
    "description": "High-performance laptop",
    "price": 1200.00,
    "productCategory": 1,
    "quantity": 5,
    "available": true,
    "date": "2025-02-01",
    "imageName": "laptop.jpg",
    "imageType": "image/jpeg",
    "image": "Base64EncodedDataHere"
  }
]
```

---
## Project Structure
```
application.backend
├── controller   # REST controllers
├── model        # Entity classes
├── repository   # Data access layer
└── service      # Business logic layer
```

---
## Future Improvements
- Implement authentication and authorization using JWT.
- Add more filtering and sorting features for product search.
- Integrate with cloud storage for image management.
- Enhance API error handling and documentation.

---
## Contribution
Contributions are welcome! Please fork the repository and submit a pull request for review.

---
## Author
- **Pasan Hewavitharana**
- GitHub: [Profile](https://github.com/pasan2002)

