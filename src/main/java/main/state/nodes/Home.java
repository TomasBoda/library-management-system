package main.state.nodes;

import main.app.App;
import main.app.Configuration;
import main.state.State;
import main.state.types.OptionState;
import main.utils.Console;

public class Home extends OptionState {

    public Home(String command, String message) {
        super(command, message);
    }
}
