package main.state.nodes.add.book;

import main.state.State;
import main.state.types.InputState;

public class AddBookDescription extends InputState {

    public AddBookDescription(String command, String message) {
        super(command, message);
    }

    public AddBookDescription(String command, String message, State callback) {
        super(command, message, callback);
    }

    public AddBookDescription(String command, String message, State[] children) {
        super(command, message, children);
    }
}
