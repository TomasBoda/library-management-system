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
        String newName = getInputs()[2];
        String newEmail = getInputs()[3];
        String newPassword = getInputs()[4];
        int newAdmin;

        try { newAdmin = Integer.parseInt(getInputs()[5]); } catch (Exception e) {
            Console.println("Admin must be a number");
            return;
        }

        if (newAdmin != 0 && newAdmin != 1) {
            Console.println("Admin must be either 0 or 1");
            return;
        }

        Response response = App.api.users().edit(user.getEmail(), new User(newName, newEmail, newPassword, newAdmin));
        Console.println(response.getMessage());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
