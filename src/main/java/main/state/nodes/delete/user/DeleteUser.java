package main.state.nodes.delete.user;

import main.api.Response;
import main.app.App;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class DeleteUser extends ActionState {

    public DeleteUser(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String email = getValues()[1];

        Response response = App.api.users().delete(email);

        if (response.getStatus() == 200) {
            Console.println("User successfully deleted");
        } else {
            Console.println(response.getMessage());
        }
    }
}
