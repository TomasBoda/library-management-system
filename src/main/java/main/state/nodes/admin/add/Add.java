package main.state.nodes.admin.add;

import main.state.State;
import main.state.types.OptionState;

public class Add extends OptionState {

    public Add(String command, String message, State callback) {
        super(command, message, callback);
    }
}
