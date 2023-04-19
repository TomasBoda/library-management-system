package main.app;

import main.api.Api;
import main.api.Response;
import main.command.Command;
import main.library.model.User;
import main.state.State;
import main.state.StateManager;
import main.utils.Console;
import main.utils.Converter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {

    public static final Api api = new Api();
    public static final StateManager manager = new StateManager();
    public static final Command[] commands = initCommands();

    public void start() {
        Console.println("Welcome to the Library Management System " + Configuration.VERSION);

        login();
        manager.build();

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

    private void login() {
        Scanner scanner = new Scanner(System.in);

        while (!logged()) {
            Console.println("Please login to continue");
            Console.print("Enter email: ");
            String email = scanner.nextLine();
            Console.print("Enter password: ");
            String password = scanner.nextLine();

            Response<User> response = App.api.auth().login(email, password);

            if (response.getStatus() == 200) {
                Configuration.userId = response.getData().getId();
                Console.println("Logged in as " + response.getData().getName());
            } else {
                Console.println("User credentials are incorrect, please try again");
            }
        }
    }

    public static boolean logged() {
        return Configuration.userId != null;
    }

    private static Command[] initCommands() {
        return new Command[] {
                new Command("back", state -> state.getCallback() == null ? state : state.getCallback()),
                new Command("home", state -> App.manager.getRootState()),
                new Command("exit", state -> { App.exit(); return null; })
        };
    }

    public static void exit() {
        Console.println("Exiting the application");
        System.exit(0);
    }
}
