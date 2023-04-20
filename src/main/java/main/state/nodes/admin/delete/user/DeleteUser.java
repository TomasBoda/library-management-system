package main.state.nodes.admin.delete.user;

import main.api.Response;
import main.app.App;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class DeleteUser extends ActionState {

    public DeleteUser(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String email = getInputs()[1];

        Response response = App.api.users().delete(email);
        Console.println(response.getMessage());
    }
}
