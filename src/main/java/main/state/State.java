package main.state;

import main.api.Response;
import main.app.App;
import main.app.Configuration;
import main.command.Command;
import main.utils.Console;

public abstract class State {

    private String command;
    private String message;

    private State[] children;
    private State callback;

    public State(String command, String message) {
        this.command = command;
        this.message = message;
    }

    public State(String command, String message, State callback) {
        this.command = command;
        this.message = message;
        this.callback = callback;
    }

    /**
     * Returns the next state of the state tree based on the input command
     * @param command user input
     * @return next state to go to
     */
    public abstract State next(String command);

    /**
     * Prints a custom message to the console when the state has been reached
     */
    public abstract void ask();

    /**
     * Processes the current user command and returns a new state to go to
     * @param command user input
     * @return next state to go to
     */
    public State process(String command) {
        // process built-in commands
        for (Command cmd : App.commands) {
            if (cmd.getCommand().equals(command)) {
                return cmd.getAction().getState(this);
            }
        }

        return next(command);
    }

    public String getCommand() {
        return command;
    }

    public String getMessage() {
        return message;
    }

    public State[] getChildren() {
        return children;
    }

    public void setChildren(State... states) {
        children = states;
    }

    public State getCallback() {
        return callback;
    }

    public void setCallback(State state) {
        callback = state;
    }

    /**
     * Generates a breadcrumbs string of the current state, so a path from the root state to the current state
     * @return breadcrumbs string together with the user's admin status
     */
    public String getBreadcrumbs() {
        String breadcrumbs = "";
        State current = this;

        while (current != null) {
            breadcrumbs = "> " + current.getCommand() + " " + breadcrumbs;
            current = current.getCallback();
        }

        Response<Integer> response = App.api.auth().isAdmin(App.userId);
        if (response.getStatus() != 200) {
            Console.println("Couldn't load the user");
            App.exit();
        }

        int admin = response.getData();
        String adminStatus = admin == 1 ? "admin" : "student";

        return "(" + adminStatus + ") " + breadcrumbs;
    }
}
