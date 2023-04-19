package main.state.types;

import main.app.App;
import main.state.State;
import main.utils.Console;

public abstract class InputState extends State {

    public InputState(String command, String message) {
        super(command, message);
    }

    public InputState(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public State next(String command) {
        return ((ActionState) getCallback()).nextChild(command);
    }

    @Override
    public void ask() {
        Console.print(getMessage() + ": " + getCustomAskValue());
    }

    public String getCustomAskValue() {
        return "";
    }
}
