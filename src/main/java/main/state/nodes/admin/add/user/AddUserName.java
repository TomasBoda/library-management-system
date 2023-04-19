package main.state.nodes.admin.add.user;

import main.state.State;
import main.state.types.InputState;

public class AddUserName extends InputState {

    public AddUserName(String command, String message, State callback) {
        super(command, message, callback);
    }
}
