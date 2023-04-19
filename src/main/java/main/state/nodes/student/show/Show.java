package main.state.nodes.student.show;

import main.state.State;
import main.state.types.OptionState;

public class Show extends OptionState {

    public Show(String command, String message) {
        super(command, message);
    }

    public Show(String command, String message, State callback) {
        super(command, message, callback);
    }
}
