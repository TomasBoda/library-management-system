package main.state.nodes.admin.add.user;

import main.app.App;
import main.api.Response;
import main.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class AddUser extends ActionState {

    public AddUser(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String name = getInputs()[1];
        String email = getInputs()[2];
        String password = getInputs()[3];
        int admin;

        try { admin = Integer.parseInt(getInputs()[4]); } catch (Exception e) {
            Console.println("Admin must be a number");
            return;
        }

        if (admin != 0 && admin != 1) {
            Console.println("Admin must be either 0 or 1");
            return;
        }

        Response response = App.api.users().add(new User(name, email, password, admin));
        Console.println(response.getMessage());
    }
}
