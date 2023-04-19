package main.state.nodes.admin.add.book;

import main.state.State;
import main.state.types.InputState;

public class AddBookTitle extends InputState {

    public AddBookTitle(String command, String message, State callback) {
        super(command, message, callback);
    }
}
