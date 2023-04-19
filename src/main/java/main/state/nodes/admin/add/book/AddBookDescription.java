package main.state.nodes.admin.add.book;

import main.state.State;
import main.state.types.InputState;

public class AddBookDescription extends InputState {

    public AddBookDescription(String command, String message, State callback) {
        super(command, message, callback);
    }
}
