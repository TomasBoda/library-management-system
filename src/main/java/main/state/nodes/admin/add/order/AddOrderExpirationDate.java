package main.state.nodes.admin.add.order;

import main.state.State;
import main.state.types.InputState;

public class AddOrderExpirationDate extends InputState {

    public AddOrderExpirationDate(String command, String message, State callback) {
        super(command, message, callback);
    }
}
