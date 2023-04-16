package main.app;

import main.api.Api;
import main.state.State;
import main.state.StateManager;
import main.utils.Console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static Api api;
    public static StateManager manager;

    public App() {
        api = new Api();
        manager = new StateManager();
    }

    public void start() {
        Console.println("Welcome to the Library Management System v" + Configuration.VERSION);
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

    public static void exit() {
        System.exit(0);
    }
}
