package main.state.nodes.student.edit.password;

import main.api.Response;
import main.app.App;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;
import main.utils.Generator;

public class EditPassword extends ActionState {

    public EditPassword(String command, String message) {
        super(command, message);
    }

    public EditPassword(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        Response<User> responseUser = App.api.profile().getProfile();

        if (responseUser.getStatus() != 200) {
            Console.println(responseUser.getMessage());
            return;
        }

        String password = responseUser.getData().getPassword();

        String oldPassword = getValues()[1];
        String newPassword = getValues()[2];
        String repeatNewPassword = getValues()[3];

        if (!password.equals(Generator.getHash(oldPassword))) {
            Console.println("Old password does not match your current password");
            return;
        }

        if (!newPassword.equals(repeatNewPassword)) {
            Console.println("The new passwords do not match");
            return;
        }

        Response response = App.api.profile().editPassword(newPassword);
        Console.println(response.getMessage());
    }
}
