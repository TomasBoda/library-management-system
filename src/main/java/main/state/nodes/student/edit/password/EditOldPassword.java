package main.state.nodes.student.edit.password;

import main.state.State;
import main.state.types.InputState;

public class EditOldPassword extends InputState {

    public EditOldPassword(String command, String message, State callback) {
        super(command, message, callback);
    }
}
