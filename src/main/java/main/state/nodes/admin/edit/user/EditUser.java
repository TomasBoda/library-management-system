package main.state.nodes.admin.edit.user;

import main.api.Response;
import main.app.App;
import main.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class EditUser extends ActionState {

    private User user;

    public EditUser(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String newName = getValues()[2];
        String newEmail = getValues()[3];
        String newPassword = getValues()[4];
        String newAdmin = getValues()[5];

        Response response = App.api.users().edit(user.getEmail(), new User(newName, newEmail, newPassword, Integer.parseInt(newAdmin)));
        Console.println(response.getMessage());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
