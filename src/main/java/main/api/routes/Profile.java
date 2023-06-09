package main.api.routes;

import main.api.Response;
import main.app.App;
import main.app.Configuration;
import main.model.User;
import main.state.nodes.student.model.OrderResult;
import main.utils.Generator;

import java.sql.*;
import java.util.ArrayList;

public class Profile {

    private Connection connection;

    public Profile(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves the logged user's profile information
     * @return response of the API call with the user's data
     */
    public Response<User> getProfile() {
        String query = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, App.userId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                int admin = result.getInt("admin");

                return new Response(200, "User profile loaded successfully", new User(id, name, email, password, admin));
            }

            return new Response(500, "User profile couldn't be loaded");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Retrieves all orders of the logged user
     * @param condition condition based on which the orders should be retrieved
     * @return response of the API call with the array of retrieved orders
     */
    public Response<OrderResult[]> getOrders(String condition) {
        String query = "SELECT books.title AS bookTitle, books.author as bookAuthor, orders.createdDate AS orderCreatedDate, orders.expirationDate AS orderExpirationDate FROM orders JOIN books ON books.id = orders.bookId WHERE orders.userId = ? AND " + condition + " ORDER BY orders.expirationDate DESC;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, App.userId);

            ResultSet result = statement.executeQuery();
            ArrayList<OrderResult> orders = new ArrayList<>();

            while (result.next()) {
                String bookTitle = result.getString("bookTitle");
                String bookAuthor = result.getString("bookAuthor");
                Date orderCreatedDate = result.getDate("orderCreatedDate");
                Date orderExpirationDate = result.getDate("orderExpirationDate");

                orders.add(new OrderResult(bookTitle, bookAuthor, orderCreatedDate, orderExpirationDate));
            }

            return new Response(200, "Orders loaded successfully", orders.toArray(OrderResult[]::new));
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Edits the name of the logged user
     * @param userName new name to be set
     * @return response of the API call
     */
    public Response editName(String userName) {
        String query = "UPDATE users SET name = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userName);
            statement.setString(2, App.userId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return new Response(200, "User name updated successfully");
            }

            return new Response(500, "User name couldn't be updated");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Edits the e-mail of the logged user
     * @param userEmail new e-mail to be set
     * @return response of the API call
     */
    public Response editEmail(String userEmail) {
        String query = "UPDATE users SET email = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userEmail);
            statement.setString(2, App.userId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return new Response(200, "User e-mail successfully");
            }

            return new Response(500, "User e-mail couldn't be updated");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Edits the password of the logged user
     * @param userPassword new password to be set
     * @return response of the API call
     */
    public Response editPassword(String userPassword) {
        String query = "UPDATE users SET password = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Generator.getHash(userPassword));
            statement.setString(2, App.userId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return new Response(200, "User password updated successfully");
            }

            return new Response(500, "User password couldn't be updated");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(500, "MySQL Error");
        }
    }
}
