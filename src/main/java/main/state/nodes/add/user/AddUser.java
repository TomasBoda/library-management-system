package main.state.nodes.add.user;

import main.app.App;
import main.api.Response;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class AddUser extends ActionState {

    public AddUser(String command, String message) {
        super(command, message);
    }

    public AddUser(String command, String message, State callback) {
        super(command, message, callback);
    }

    public AddUser(String command, String message, State[] children) {
        super(command, message, children);
    }

    @Override
    public void execute() {
        String name = getValues()[1];
        String email = getValues()[2];
        Response response = App.api.users().add(new User(name, email));
        Console.println(response.getMessage());
    }
}
