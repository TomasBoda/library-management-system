package main.state.nodes.list;

import main.state.State;
import main.state.types.OptionState;

public class List extends OptionState {

    public List(String command, String message) { super(command, message); }

    public List(String command, String message, State callback) { super(command, message, callback); }

    public List(String command, String message, State[] children) {
        super(command, message, children);
    }
}
