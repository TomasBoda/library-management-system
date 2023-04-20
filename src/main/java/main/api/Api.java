package main.api;

import java.sql.*;
import java.util.Scanner;

import main.api.routes.*;
import main.app.App;
import main.app.Configuration;
import main.utils.Console;

public class Api {

    private Connection connection;

    private Users users;
    private Books books;
    private Orders orders;
    private Authentification auth;
    private Profile profile;

    public Api() {
        connect();

        users = new Users(connection);
        books = new Books(connection);
        orders = new Orders(connection);
        auth = new Authentification(connection);
        profile = new Profile(connection);
    }

    public Users users() {
        return users;
    }

    public Books books() {
        return books;
    }

    public Orders orders() {
        return orders;
    }

    public Authentification auth() {
        return auth;
    }

    public Profile profile() {
        return profile;
    }

    /**
     * Connects the system to the MySQL database
     * Initiates a connection to the MySQL database
     */
    private void connect() {
        checkDatabasePassword();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Configuration.DATABASE_URL, Configuration.DATABASE_USERNAME, Configuration.DATABASE_PASSWORD);
        } catch (Exception e) {
            Console.println("Cannot connect to the database, exiting...");
            App.exit();
        }
    }

    /**
     * Checks whether the database password has been provided and if not, prompts the user to enter it into the console
     */
    private void checkDatabasePassword() {
        if (Configuration.DATABASE_PASSWORD.equals("")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter database password: ");
            String password = scanner.nextLine();
            Configuration.DATABASE_PASSWORD = password;
        }
    }
}
