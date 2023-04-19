package main.state.nodes.add.user;

import main.state.State;
import main.state.types.InputState;

public class AddUserEmail extends InputState {

    public AddUserEmail(String command, String message, State callback) {
        super(command, message, callback);
    }
}
