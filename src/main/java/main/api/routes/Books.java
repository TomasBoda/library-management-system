package main.api.routes;

import main.api.Response;
import main.library.model.Book;
import main.library.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Books {

    private Connection connection;

    public Books(Connection connection) {
        this.connection = connection;
    }

    public Response add(Book book) {
        String query = "INSERT INTO books (title, description, author) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getDescription());
            statement.setString(3, book.getAuthor());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return new Response(200, "Book added successfully");
            }

            return new Response(500, "Book couldn't be added");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    public Response get() {
        return null;
    }

    public Response<Book[]> getAll() {
        String query = "SELECT * FROM books";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            ArrayList<Book> books = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                String title = result.getString("title");
                String description = result.getString("description");
                String author = result.getString("author");
                books.add(new Book(id, title, description, author));
            }

            Book[] bookArray = books.toArray(Book[]::new);
            return new Response(200, "Books loaded successfully", bookArray);
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }
}
