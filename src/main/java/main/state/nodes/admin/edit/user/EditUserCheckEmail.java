package main.state.nodes.admin.edit.user;

import main.api.Response;
import main.app.App;
import main.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.state.types.InputState;
import main.utils.Console;

public class EditUserCheckEmail extends InputState {

    public EditUserCheckEmail(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public State next(String command) {
        Response<User> response = App.api.users().getByEmail(command);

        if (response.getStatus() == 200) {
            ((EditUser) getCallback()).setUser(response.getData());
            return ((ActionState) getCallback()).nextChild(command);
        }

        Console.println("User with the given e-mail does not exist");
        return getCallback().getCallback();
    }
}
