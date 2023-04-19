package main.state.nodes.add.order;

import main.api.Response;
import main.app.App;
import main.library.model.User;
import main.state.State;
import main.state.nodes.edit.user.EditUser;
import main.state.types.ActionState;
import main.state.types.InputState;
import main.utils.Console;

public class AddOrderUser extends InputState {

    public AddOrderUser(String command, String message) {
        super(command, message);
    }

    public AddOrderUser(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public State next(String command) {
        Response<User> response = App.api.users().getByEmail(command);

        if (response.getStatus() == 200) {
            return ((ActionState) getCallback()).nextChild(response.getData().getId());
        }

        Console.println("User with the given e-mail does not exist");
        return getCallback().getCallback();
    }
}
