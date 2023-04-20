package main.api.routes;

import main.api.Response;
import main.model.Order;
import main.state.nodes.admin.model.OrderResult;

import java.sql.*;
import java.util.ArrayList;

public class Orders {

    private Connection connection;

    public Orders(Connection connection) {
        this.connection = connection;
    }

    public Response add(Order order) {
        String query = "INSERT INTO orders (userId, bookId, createdDate, expirationDate) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, order.getUserId());
            statement.setString(2, order.getBookId());
            statement.setDate(3, order.getCreatedDate());
            statement.setDate(4, order.getExpirationDate());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return new Response(200, "Order added successfully");
            }

            return new Response(500, "Order couldn't be added");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(500, "MySQL Error");
        }
    }

    public Response edit(String userId, String bookId, Order order) {
        String query = "UPDATE orders SET expirationDate = ? WHERE userId = ? AND bookId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, order.getExpirationDate());
            statement.setString(2, userId);
            statement.setString(3, bookId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return new Response(200, "Order updated successfully");
            }

            return new Response(500, "Order couldn't be updated");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    public Response delete(String userId, String bookId) {
        String query = "DELETE FROM orders WHERE userId = ? AND bookId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, bookId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return new Response(500, "Order couldn't be deleted");
            }

            return new Response(200, "Order successfully deleted");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    public Response<Order> getById(String userId, String bookId) {
        String query = "SELECT * FROM orders WHERE userId = ? AND bookId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            statement.setString(2, bookId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Date createdDate = result.getDate("createdDate");
                Date expirationDate = result.getDate("expirationDate");

                return new Response(200, "Order exists", new Order(userId, bookId, createdDate, expirationDate));
            }

            return new Response(500, "Order does not exist");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    public Response<OrderResult[]> getAll(String condition) {
        String query = "SELECT users.id AS userId, users.name AS userName, users.email AS userEmail, books.id AS bookId, books.title AS bookTitle, books.author as bookAuthor, orders.createdDate AS orderCreatedDate, orders.expirationDate AS orderExpirationDate FROM users JOIN orders ON users.id = orders.userId JOIN books ON books.id = orders.bookId " + condition + " GROUP BY users.id, books.id ORDER BY orders.expirationDate DESC;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            ArrayList<OrderResult> orders = new ArrayList<>();

            while (result.next()) {
                String userId = result.getString("userId");
                String userName = result.getString("userName");
                String userEmail = result.getString("userEmail");
                String bookId = result.getString("bookId");
                String bookTitle = result.getString("bookTitle");
                String bookAuthor = result.getString("bookAuthor");
                Date orderCreatedDate = result.getDate("orderCreatedDate");
                Date orderExpirationDate = result.getDate("orderExpirationDate");

                orders.add(new OrderResult(userId, userName, userEmail, bookId, bookTitle, bookAuthor, orderCreatedDate, orderExpirationDate));
            }

            return new Response(200, "Orders loaded successfully", orders.toArray(OrderResult[]::new));
        } catch (SQLException e) {
            return new Response(500, e.getMessage());
        }
    }
}
