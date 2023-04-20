package main.state.nodes.admin.delete.order;

import main.state.State;
import main.state.types.InputState;

public class DeleteOrderCheckUser extends InputState {

    public DeleteOrderCheckUser(String command, String message, State callback) {
        super(command, message, callback);
    }
}
