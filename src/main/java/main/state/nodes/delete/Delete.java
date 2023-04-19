package main.state.nodes.delete;

import main.state.State;
import main.state.types.OptionState;

public class Delete extends OptionState {

    public Delete(String command, String message, State callback) {
        super(command, message, callback);
    }
}
