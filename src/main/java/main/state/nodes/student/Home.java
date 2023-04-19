package main.state.nodes.student;

import main.state.State;
import main.state.types.OptionState;

public class Home extends OptionState {
    
    public Home(String command, String message) {
        super(command, message);
    }

    public Home(String command, String message, State callback) {
        super(command, message, callback);
    }
}
