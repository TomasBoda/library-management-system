package main.state.nodes.edit;

import main.state.State;
import main.state.types.OptionState;

public class Edit extends OptionState {

    public Edit(String command, String message, State callback) {
        super(command, message, callback);
    }
}
