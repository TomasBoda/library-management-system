package main.state.nodes.admin.edit.user;

import main.state.State;
import main.state.types.InputState;

public class EditUserName extends InputState {

    public EditUserName(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public String getCustomAskValue() {
        return "(" + ((EditUser) getCallback()).getUser().getName() + ") ";
    }
}
