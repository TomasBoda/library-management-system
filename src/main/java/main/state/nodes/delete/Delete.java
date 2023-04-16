package main.state.nodes.delete;

import main.state.State;
import main.state.types.OptionState;

public class Delete extends OptionState {

    public Delete(String command, String message) { super(command, message); }

    public Delete(String command, String message, State callback) { super(command, message, callback); }

    public Delete(String command, String message, State[] children) {
        super(command, message, children);
    }
}
