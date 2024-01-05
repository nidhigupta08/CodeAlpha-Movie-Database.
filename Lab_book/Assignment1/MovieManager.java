import java.sql.*;
import java.util.Scanner;

public class MovieManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Movie_Database";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "system";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS movies ( " +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "title VARCHAR(100) NOT NULL, " +
            "genre VARCHAR(50), " +
            "release_year INT, " +
            "director VARCHAR(100) " +
            ")";

    private Connection connection;

    public MovieManager() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            createMoviesTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createMoviesTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_TABLE_QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMovie(String title, String genre, int releaseYear, String director) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO movies(title, genre, release_year, director) VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, genre);
            preparedStatement.setInt(3, releaseYear);
            preparedStatement.setString(4, director);

            preparedStatement.executeUpdate();
            System.out.println("Movie added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchMovieByTitle(String searchTitle) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movies WHERE title LIKE ?");
            preparedStatement.setString(1, "%" + searchTitle + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Genre: " + resultSet.getString("genre"));
                System.out.println("Release Year: " + resultSet.getInt("release_year"));
                System.out.println("Director: " + resultSet.getString("director"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAllMovies() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movies");

            while (resultSet.next()) {
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Genre: " + resultSet.getString("genre"));
                System.out.println("Release Year: " + resultSet.getInt("release_year"));
                System.out.println("Director: " + resultSet.getString("director"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MovieManager movieManager = new MovieManager();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a movie");
            System.out.println("2. Search for a movie by title");
            System.out.println("3. Display all movies");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter movie details:");
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Release Year: ");
                    int releaseYear = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Director: ");
                    String director = scanner.nextLine();
                    movieManager.addMovie(title, genre, releaseYear, director);
                    break;
                case 2:
                    System.out.print("Enter movie title to search: ");
                    String searchTitle = scanner.nextLine();
                    movieManager.searchMovieByTitle(searchTitle);
                    break;
                case 3:
                    movieManager.displayAllMovies();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }

        // Close resources
        scanner.close();
        try {
            if (movieManager.connection != null && !movieManager.connection.isClosed()) {
                movieManager.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
