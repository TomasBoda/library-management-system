package main.state.nodes.admin.add.order;

import main.state.State;
import main.state.types.InputState;

public class AddOrderCreatedDate extends InputState {

    public AddOrderCreatedDate(String command, String message) {
        super(command, message);
    }

    public AddOrderCreatedDate(String command, String message, State callback) {
        super(command, message, callback);
    }
}
