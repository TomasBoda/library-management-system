package main.api;

import java.sql.*;
import java.util.Scanner;

import main.api.routes.*;
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

    public Orders orders() {
        return new Orders(connection);
    }

    public Authentification auth() {
        return new Authentification(connection);
    }

    public Profile profile() {
        return new Profile(connection);
    }

    private void connect() {
        if (Configuration.DATABASE_PASSWORD.equals("")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter database password: ");
            String password = scanner.nextLine();
            Configuration.DATABASE_PASSWORD = password;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(Configuration.DATABASE_URL, Configuration.DATABASE_USERNAME, Configuration.DATABASE_PASSWORD);
        } catch (Exception e) {
            Console.println("Cannot connect to the database, exiting...");
            App.exit();
        }
    }
}
