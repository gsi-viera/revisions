ECASA Inventory Management API
This API allows the ECASA company to control the inventory of products in its warehouse. The API can be consumed from a mobile application and allows movement throughout the warehouse.

Warehouse Features
The warehouse is divided into sections. Each section has a size in square meters and is intended for a specific type of product: Appliances, Meats, Clothing or Hygiene.

Each product has the following characteristics:
Size
Color
Price
Fragility (yes/no)
Type of packaging (Cardboard, Plastic, Glass or Nylon)
Batch
Roles
There are two roles defined to operate the system: Administrator and Operator.

Functionalities
The API allows you to perform the following operations:
 *Section management
 *Product management
 *Inventory management
 *User management

Technologies used
The API is implemented in Spring Boot.
The authentication was developed using JWT
Java version 17.
PostgreSQL as DB Manager

The full API documentation can be consulted in: https://documenter.getpostman.com/view/7130051/2s93K1pzzn