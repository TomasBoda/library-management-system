package main.api.routes;

import main.api.Response;
import main.model.Book;
import main.utils.Generator;

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

    /**
     * Adds a new book to the database
     * @param book book object to be added
     * @return reponse of the API call
     */
    public Response add(Book book) {
        String query = "INSERT INTO books (id, title, description, author, stock) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Generator.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getDescription());
            statement.setString(4, book.getAuthor());
            statement.setInt(5, book.getStock());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return new Response(200, "Book added successfully");
            }

            return new Response(500, "Book couldn't be added");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Edits a book's data in the database
     * @param bookTitle title of the book to be edited
     * @param book data of the book to be edited
     * @return response of the API call
     */
    public Response edit(String bookTitle, Book book) {
        String query = "UPDATE books SET title = ?, description = ?, author = ?, stock = ? WHERE title = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getDescription());
            statement.setString(3, book.getAuthor());
            statement.setInt(4, book.getStock());
            statement.setString(5, bookTitle);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return new Response(200, "Book updated successfully");
            }

            return new Response(500, "Book couldn't be updated");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Deletes a book from the database
     * @param bookTitle title of the book to be deleted
     * @return response of the API call
     */
    public Response delete(String bookTitle) {
        String query = "DELETE FROM books WHERE title = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bookTitle);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return new Response(500, "Book couldn't be deleted");
            }

            return new Response(200, "Book successfully deleted");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Retrieve a book from the database
     * @param bookTitle title of the book to be retrieved
     * @return response of the API call with the retrieved book's data
     */
    public Response<Book> getByTitle(String bookTitle) {
        String query = "SELECT * FROM books WHERE title = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bookTitle);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String id = result.getString("id");
                String title = result.getString("title");
                String description = result.getString("description");
                String author = result.getString("author");
                int stock = result.getInt("stock");

                return new Response(200, "Book exists", new Book(id, title, description, author, stock));
            }

            return new Response(500, "User does not exist");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Retrieves all books from the database
     * @return array of books from the database
     */
    public Response<Book[]> getAll() {
        String query = "SELECT * FROM books";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            ArrayList<Book> books = new ArrayList<>();

            while (result.next()) {
                String id = result.getString("id");
                String title = result.getString("title");
                String description = result.getString("description");
                String author = result.getString("author");
                int stock = result.getInt("stock");
                books.add(new Book(id, title, description, author, stock));
            }

            return new Response(200, "Books loaded successfully", books.toArray(Book[]::new));
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }
}
