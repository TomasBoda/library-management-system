package main.state.nodes.student.edit.name;

import main.state.State;
import main.state.types.InputState;

public class EditCheckName extends InputState {

    public EditCheckName(String command, String message, State callback) {
        super(command, message, callback);
    }
}
