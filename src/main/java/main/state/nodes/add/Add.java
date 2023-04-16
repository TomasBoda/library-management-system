package main.state.nodes.add;

import main.state.State;
import main.state.types.OptionState;

public class Add extends OptionState {

    public Add(String command, String message) { super(command, message); }

    public Add(String command, String message, State callback) { super(command, message, callback); }

    public Add(String command, String message, State[] children) {
        super(command, message, children);
    }
}
