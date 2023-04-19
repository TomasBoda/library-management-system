package main.state.nodes.edit.user;

import main.state.State;
import main.state.types.InputState;

public class EditUserEmail extends InputState {

    public EditUserEmail(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public String getCustomAskValue() {
        return "(" + ((EditUser) getCallback()).getUser().getEmail() + ") ";
    }
}
