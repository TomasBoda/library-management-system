package main.state.nodes.admin.add.book;

import main.state.State;
import main.state.types.InputState;

public class AddBookStock extends InputState {

    public AddBookStock(String command, String message, State callback) {
        super(command, message, callback);
    }
}
