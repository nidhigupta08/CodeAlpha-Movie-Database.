Title :Movie Database
This Java-based Movie Database project serves as a foundational tool for managing movie records. It's designed for developers and enthusiasts seeking to understand database connectivity using
JDBC with MySQL in a Java environment. The project facilitates adding, searching, and displaying movie details via a user-friendly console interface. It's an educational resource demonstrating
fundamental database operations and serves as a starting point for individuals keen on exploring database management systems and Java programming.

Project Overview:
The project aims to create a Movie Database application utilizing Java and JDBC (Java Database Connectivity) for managing movie records.

Features:
1) Database Connection:
Establishes a connection to a MySQL database using JDBC.
Creates a 'movies' table to store movie details if it doesn't exist.
2) Functionality:
Add Movie: Accepts user input for movie details (title, genre, release year, director) and adds a new movie record to the database.
Search Movie by Title: Allows users to search for movies by providing a partial or complete title match.
Displays movie details based on the search criteria.
Display All Movies: Retrieves and displays all movies present in the database.

3) Menu-Driven Interface:
Presents a user-friendly menu using switch-case statements for interaction.
Options include adding a new movie, searching for a movie by title, displaying all movies, and exiting the application.

Technical Details:
Language & Tools: Java programming language, JDBC for database connectivity, MySQL for database management.

Classes:
MovieManager: Manages database connection, table creation, movie addition, searching, and display operations.

Database Schema:
Contains a single table named 'movies' with columns for ID, title, genre, release year, and director.

Setup Java Development Environment:
Ensure you have Java Development Kit (JDK) installed on your system.
Set up your preferred Integrated Development Environment (IDE) like Eclipse, IntelliJ IDEA, or use a text editor and the command line.

Download MySQL and MySQL Connector/J:
Install MySQL Server on your machine if you haven't already.
Download MySQL Connector/J, the JDBC driver for MySQL, and include it in your project's classpath.

Code Setup:
Write the Java code for the movie database into a new Java file in your IDE or text editor.

Compile and Run:
Compile the Java code (javac filename.java) containing the movie database using your IDE or command line.
Run the compiled Java program using "java filename".

