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

    public abstract void execute();

    @Override
    public State next(String command) {
        if (currentStateIndex == 0) {
            return nextChild(command);
        }

        return getChildren()[currentStateIndex].next(command);
    }

    public State nextChild(String command) {
        if (values == null) {
            execute();
            currentStateIndex = 0;
            return getCallback();
        }

        values[currentStateIndex] = command;

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

        //App.manager.setState(next(""));
        //App.manager.getState().ask();
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
