package main.state.nodes.add.user;

import main.state.State;
import main.state.types.InputState;

public class AddUserName extends InputState {

    public AddUserName(String command, String message) {
        super(command, message);
    }

    public AddUserName(String command, String message, State callback) {
        super(command, message, callback);
    }

    public AddUserName(String command, String message, State[] children) {
        super(command, message, children);
    }
}
