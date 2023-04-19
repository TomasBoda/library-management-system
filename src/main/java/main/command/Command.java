package main.command;

import main.state.State;

public class Command {

    private String command;
    private Action action;

    public Command(String command, Action action) {
        this.command = ":" + command;
        this.action = action;
    }

    public String getCommand() {
        return command;
    }

    public Action getAction() {
        return action;
    }
}
