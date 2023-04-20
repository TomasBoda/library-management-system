package main.api.routes;

import main.api.Response;
import main.model.User;
import main.utils.Generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentification {

    private Connection connection;

    public Authentification(Connection connection) {
        this.connection = connection;
    }

    /**
     * Checks whether a user with the given e-mail and password exists and if yes, returns their data
     * @param userEmail e-mail of the user to be logged in
     * @param userPassword password of the user to be logged in
     * @return response of the API call with the logged user data
     */
    public Response<User> login(String userEmail, String userPassword) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userEmail);
            statement.setString(2, Generator.getHash(userPassword));
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                int admin = result.getInt("admin");

                return new Response(200, "User logged in successfully", new User(id, name, email, password, admin));
            }

            return new Response(404, "User was not found");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Checks whether the given user has the status of an administrator
     * @param userId ID of the user to be checked for administrator status
     * @return response of the API call with the admin status of the given user
     */
    public Response<Integer> isAdmin(String userId) {
        String query = "SELECT admin FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int admin = result.getInt("admin");
                return new Response(200, "User admin retrieved successfully", admin);
            }

            return new Response(404, "User was not found");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }
}
