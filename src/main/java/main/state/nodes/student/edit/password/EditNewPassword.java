package main.state.nodes.student.edit.password;

import main.state.State;
import main.state.types.InputState;

public class EditNewPassword extends InputState {

    public EditNewPassword(String command, String message, State callback) {
        super(command, message, callback);
    }
}
