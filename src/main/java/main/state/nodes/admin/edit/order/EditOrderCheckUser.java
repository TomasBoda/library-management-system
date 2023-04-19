package main.state.nodes.admin.edit.order;

import main.state.State;
import main.state.types.InputState;

public class EditOrderCheckUser extends InputState {

    public EditOrderCheckUser(String command, String message) {
        super(command, message);
    }

    public EditOrderCheckUser(String command, String message, State callback) {
        super(command, message, callback);
    }
}
