package main.state.nodes.add.user;

import main.app.App;
import main.api.Response;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class AddUser extends ActionState {

    public AddUser(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String name = getValues()[1];
        String email = getValues()[2];
        String password = getValues()[3];
        String admin = getValues()[4];

        Response response = App.api.users().add(new User(name, email, password, Integer.parseInt(admin)));
        Console.println(response.getMessage());
    }
}
