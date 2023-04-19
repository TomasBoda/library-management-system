package main.app;

import main.api.Api;
import main.api.Response;
import main.library.model.User;
import main.state.State;
import main.state.StateManager;
import main.utils.Console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {

    public static Api api;
    public static StateManager manager;

    public App() {
        api = new Api();
        manager = new StateManager();
    }

    public void start() {
        Console.println("Welcome to the Library Management System v" + Configuration.VERSION);

        mockLogin();
        authenticate();

        manager.getState().ask();
        CommandExecutor executor = (line) -> {
            State next = manager.getState().process(line);
            manager.setState(next);
            manager.getState().ask();
        };

        run(executor);
    }

    private void run(CommandExecutor executor) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;

            while ((line = reader.readLine()) != null) {
                executor.execute(line);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            Console.error("Cannot read input, exiting...");
            App.exit();
        } catch (IOException e) {
            Console.error("Cannot read input, exiting...");
            App.exit();
        }
    }

    private void authenticate() {
        Scanner scanner = new Scanner(System.in);

        while (Configuration.user == null) {
            Console.println("Please login to continue");
            Console.print("Enter email: ");
            String email = scanner.nextLine();
            Console.print("Enter password: ");
            String password = scanner.nextLine();

            Response<User> response = App.api.auth().login(email, password);

            if (response.getStatus() == 200) {
                Configuration.user = response.getData();
                Console.println("Logged in as " + Configuration.user.getName());
            } else {
                Console.println("User credentials are incorrect, please try again");
            }
        }
    }

    private void mockLogin() {
        Configuration.user = new User("8c480d9fbf6f6be770f08537c601f98f", "Tomas Boda", "tomasboda@email.com", "2f21e282e693bfcdfaf97fb5e26d857f", 1);
    }

    public static void exit() {
        System.exit(0);
    }
}
