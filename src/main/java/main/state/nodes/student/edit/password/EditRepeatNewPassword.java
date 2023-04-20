package main.state.nodes.student.edit.password;

import main.state.State;
import main.state.types.InputState;

public class EditRepeatNewPassword extends InputState {

    public EditRepeatNewPassword(String command, String message, State callback) {
        super(command, message, callback);
    }
}
