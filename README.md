# Order-Management-Online-Grocery
A RESTful API service using Spring Boot to manage an online grocery ordering system for customers, grocery items, and orders. The system support CRUD operations for each entity, focusing on basic data management without complex business logic.

This is a simplified online grocery system aimed at maintaining records of customers, grocery items, and orders.
Entities:
Customer: Stores customer information.
Grocery Item: Represents the available grocery items.
Order: Tracks orders placed by customers for grocery items.

Relationships:
A customer can place multiple orders.
An order can contain one or more grocery items.
A grocery item can be part of multiple orders.

CRUD Operations
Customer Management:
Fields: Name, Email, Address, Phone
Perform CRUD operations to manage customer information.

Grocery Item Management:
Fields: Name, Category, Price, Quantity
CRUD operations to manage grocery items.

Order Management:
Fields: Customer, Grocery Item(s), Order Date, Total Price
CRUD operations to manage order details.
