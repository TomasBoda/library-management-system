package main.api;

import java.sql.*;

import main.api.routes.Authentification;
import main.api.routes.Books;
import main.api.routes.Users;
import main.app.App;
import main.app.Configuration;
import main.utils.Console;


public class Api {

    private Connection connection;

    public Api() {
        connect();
    }

    public Users users() {
        return new Users(connection);
    }

    public Books books() {
        return new Books(connection);
    }

    public Authentification auth() {
        return new Authentification(connection);
    }

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Configuration.DATABASE_URL, Configuration.DATABASE_USERNAME, Configuration.DATABASE_PASSWORD);
        } catch (Exception e) {
            Console.println("Cannot connect to the database, exiting...");
            App.exit();
        }
    }
}
