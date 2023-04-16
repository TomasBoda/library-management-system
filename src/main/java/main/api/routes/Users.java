package main.api.routes;

import main.api.Response;
import main.library.model.User;
import main.utils.Console;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Users {

    private Connection connection;

    public Users(Connection connection) { this.connection = connection; }

    public Response add(User user) {
        String query = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return new Response(200, "User added successfully");
            }

            return new Response(500, "User couldn't be added");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    public Response get() {
        return null;
    }

    public Response<User[]> getAll() {
        String query = "SELECT * FROM users";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String email = result.getString("email");
                users.add(new User(id, name, email));
            }

            User[] userArray = users.toArray(User[]::new);
            return new Response(200, "Users loaded successfully", userArray);
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }
}
