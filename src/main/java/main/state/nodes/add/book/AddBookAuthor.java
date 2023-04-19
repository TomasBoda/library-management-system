package main.state.nodes.add.book;

import main.state.State;
import main.state.types.InputState;

public class AddBookAuthor extends InputState {

    public AddBookAuthor(String command, String message, State callback) {
        super(command, message, callback);
    }
}
