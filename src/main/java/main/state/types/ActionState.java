package main.state.types;

import main.state.State;
import main.utils.Console;

public abstract class ActionState extends State {

    private int currentStateIndex = 0;
    private String[] inputs;

    public ActionState(String command, String message) {
        super(command, message);
    }

    public ActionState(String command, String message, State callback) {
        super(command, message, callback);
    }

    public abstract void execute();

    @Override
    public State next(String command) {
        if (currentStateIndex == 0) {
            return nextChild(command);
        }

        return getChildren()[currentStateIndex].next(command);
    }

    public State nextChild(String command) {
        if (inputs == null) {
            execute();
            currentStateIndex = 0;
            return getCallback();
        }

        inputs[currentStateIndex] = command;

        if (isAtEnd()) {
            execute();
            currentStateIndex = 0;
            return getCallback();
        }

        return getChildren()[currentStateIndex++];
    }

    @Override
    public void ask() {
        Console.println(getBreadcrumbs());
        Console.print(getMessage() + ": ");
        currentStateIndex = 0;
    }

    @Override
    public void setChildren(State... states) {
        super.setChildren(states);
        inputs = new String[getChildren().length + 1];
    }

    public String[] getInputs() {
        return inputs;
    }

    private boolean isAtEnd() {
        return currentStateIndex == getChildren().length;
    }
}
