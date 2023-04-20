package main.state.types;

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

    /**
     * Defines the custom value to be printed after the state's defined message
     * @return custom additional message to be printed to the console
     */
    public String getCustomAskValue() {
        return "";
    }
}
