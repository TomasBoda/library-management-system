package main.state.nodes.admin.add.user;

import main.state.State;
import main.state.types.InputState;

public class AddUserAdmin extends InputState {

    public AddUserAdmin(String command, String message, State callback) {
        super(command, message, callback);
    }
}
