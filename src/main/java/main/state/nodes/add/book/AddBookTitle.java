package main.state.nodes.add.book;

import main.state.State;
import main.state.types.InputState;

public class AddBookTitle extends InputState {

    public AddBookTitle(String command, String message) {
        super(command, message);
    }

    public AddBookTitle(String command, String message, State callback) {
        super(command, message, callback);
    }

    public AddBookTitle(String command, String message, State[] children) {
        super(command, message, children);
    }
}
