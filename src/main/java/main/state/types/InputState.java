package main.state.types;

import main.app.App;
import main.state.State;
import main.utils.Console;

public abstract class InputState extends State {

    public InputState(String command, String message) { super(command, message); }

    public InputState(String command, String message, State callback) { super(command, message, callback); }

    public InputState(String command, String message, State[] children) { super(command, message, children); }

    @Override
    public State next(String command) {
        return getCallback().next(command);
    }

    @Override
    public void ask() {
        Console.print(getMessage() + ": ");
    }
}
