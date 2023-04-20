package main.state.nodes.student.edit.email;

import main.state.State;
import main.state.types.InputState;

public class EditCheckEmail extends InputState {

    public EditCheckEmail(String command, String message, State callback) {
        super(command, message, callback);
    }
}
