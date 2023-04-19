package main.state.nodes.edit.user;

import main.state.State;
import main.state.types.InputState;

public class EditUserPassword extends InputState {

    public EditUserPassword(String command, String message, State callback) {
        super(command, message, callback);
    }
}
