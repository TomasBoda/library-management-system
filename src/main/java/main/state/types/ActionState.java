package main.state.types;

import main.app.App;
import main.state.State;
import main.utils.Console;

public abstract class ActionState extends State {

    private int currentStateIndex = 0;
    private String[] values;

    public ActionState(String command, String message) {
        super(command, message);
    }

    public ActionState(String command, String message, State callback) {
        super(command, message, callback);
    }

    public ActionState(String command, String message, State[] children) {
        super(command, message, children);
    }

    public abstract void execute();

    @Override
    public State next(String command) {
        if (values == null) {
            execute();
            return getCallback();
        }

        values[currentStateIndex] = command;

        if (isAtEnd()) {
            execute();
            return getCallback();
        }

        return getChildren()[currentStateIndex++];
    }

    @Override
    public void ask() {
        Console.println("-------------------------------");
        Console.println(getBreadcrumbs());
        Console.println(getMessage());
        App.manager.setState(next(""));
        App.manager.getState().ask();
    }

    @Override
    public void setChildren(State... states) {
        super.setChildren(states);
        values = new String[getChildren().length + 1];
    }

    public String[] getValues() {
        return values;
    }

    private boolean isAtEnd() {
        return currentStateIndex == getChildren().length;
    }
}
