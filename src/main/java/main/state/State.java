package main.state;

import main.app.App;
import main.utils.Console;

import java.io.IOException;
import java.util.List;

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

    public State(String command, String message, State[] children) {
        this.command = command;
        this.message = message;
        this.children = children;
    }

    public State process(String command) {
        if (command.equals(":back")) {
            if (getCallback() == null) {
                return this;
            }

            return getCallback();
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

    public void setChildren(State... states) {
        children = states;
    }
    public State[] getChildren() {
        return children;
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

        return breadcrumbs;
    }
}
