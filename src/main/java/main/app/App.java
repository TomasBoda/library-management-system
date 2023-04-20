package main.app;

import main.api.Api;
import main.api.Response;
import main.command.Command;
import main.model.User;
import main.state.State;
import main.state.StateManager;
import main.utils.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {

    private interface CommandExecutor {
        void execute(String command);
    }

    public static final Api api = new Api();
    public static final StateManager manager = new StateManager();
    public static final Command[] commands = initCommands();

    public static String userId;

    /**
     * Starts the application and initiates user input processing
     */
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

    /**
     * Reads the user input and processes it
     * @param executor the executor which processes the user input
     */
    private void run(CommandExecutor executor) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;

            while ((line = reader.readLine()) != null) {
                executor.execute(line);
            }

            reader.close();
        } catch (IOException e) {
            Console.error("Cannot read input, exiting...");
            App.exit();
        }
    }

    /**
     * Initiates the login state which is repeatedly called until the user has successfully logged in
     */
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
                App.userId = response.getData().getId();
                Console.println("Logged in as " + response.getData().getName());
            } else {
                Console.println("User credentials are incorrect, please try again");
            }
        }
    }

    /**
     * Checks whether the user is logged into the system or not
     */
    public static boolean logged() {
        return App.userId != null;
    }

    /**
     * Initiates global built-in commands
     * @return array of built-in commands
     */
    private static Command[] initCommands() {
        return new Command[] {
                new Command("back", state -> state.getCallback() == null ? state : state.getCallback()),
                new Command("home", state -> App.manager.getRootState()),
                new Command("exit", state -> { App.exit(); return null; })
        };
    }

    /**
     * Exits the application with an informative message
     */
    public static void exit() {
        Console.println("Exiting the application");
        System.exit(0);
    }
}
