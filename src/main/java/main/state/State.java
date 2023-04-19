package main.state;

import main.app.App;
import main.app.Configuration;
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

    public State process(String command) {
        if (command.equals(":back")) {
            return getCallback() == null ? this : getCallback();
        }

        return next(command);
    }

    public abstract State next(String command);

    public abstract void ask();

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

    public String getBreadcrumbs() {
        String breadcrumbs = "";
        State current = this;

        while (current != null) {
            breadcrumbs = "> " + current.getCommand() + " " + breadcrumbs;
            current = current.getCallback();
        }

        String adminStatus = Configuration.user.getAdmin() == 1 ? "admin" : "student";

        return "(" + adminStatus + ") " + breadcrumbs;
    }
}
