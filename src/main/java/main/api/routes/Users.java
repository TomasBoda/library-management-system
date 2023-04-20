package main.api.routes;

import main.api.Response;
import main.model.User;
import main.utils.Generator;

import java.sql.*;
import java.util.ArrayList;

public class Users {

    private Connection connection;

    public Users(Connection connection) {
        this.connection = connection;
    }

    /**
     * Adds a new user to the database
     * @param user user to be added to the database
     * @return response of the API call
     */
    public Response add(User user) {
        String query = "INSERT INTO users (id, name, email, password, admin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Generator.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, Generator.getHash(user.getPassword()));
            statement.setInt(5, user.getAdmin());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return new Response(200, "User added successfully");
            }

            return new Response(500, "User couldn't be added");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Edits an existing user in the database
     * @param userEmail e-mail of the user to be edited
     * @param user user data to be set
     * @return response of the API call
     */
    public Response edit(String userEmail, User user) {
        String query = "UPDATE users SET name = ?, email = ?, password = ?, admin = ? WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, Generator.getHash(user.getPassword()));
            statement.setInt(4, user.getAdmin());
            statement.setString(5, userEmail);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return new Response(200, "User updated successfully");
            }

            return new Response(500, "User couldn't be updated");
        } catch (SQLException e) {
            e.printStackTrace();
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Deletes an existing user from the database
     * @param userEmail e-mail of the user to be deleted
     * @return response of the API call
     */
    public Response delete(String userEmail) {
        String query = "DELETE FROM users WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userEmail);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                return new Response(500, "User couldn't be deleted");
            }

            return new Response(200, "User successfully deleted");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Retrieves an existing user from the database
     * @param userEmail e-mail of the user to be retrieved
     * @return response of the API call with the user data
     */
    public Response<User> getByEmail(String userEmail) {
        String query = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userEmail);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                int admin = result.getInt("admin");

                return new Response(200, "User exists", new User(id, name, email, password, admin));
            }

            return new Response(500, "User does not exist");
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }

    /**
     * Retrieves all users from the database
     * @return response of the API call with the array of retrieved users
     */
    public Response<User[]> getAll() {
        String query = "SELECT * FROM users";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet result = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();

            while (result.next()) {
                String id = result.getString("id");
                String name = result.getString("name");
                String email = result.getString("email");
                String password = result.getString("password");
                int admin = result.getInt("admin");
                users.add(new User(id, name, email, password, admin));
            }

            return new Response(200, "Users loaded successfully", users.toArray(User[]::new));
        } catch (SQLException e) {
            return new Response(500, "MySQL Error");
        }
    }
}
