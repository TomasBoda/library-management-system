package main.state.nodes.delete.order;

import main.state.State;
import main.state.types.InputState;

public class DeleteOrderCheckUser extends InputState {

    public DeleteOrderCheckUser(String command, String message) {
        super(command, message);
    }

    public DeleteOrderCheckUser(String command, String message, State callback) {
        super(command, message, callback);
    }
}
