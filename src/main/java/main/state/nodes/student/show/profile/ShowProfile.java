package main.state.nodes.student.show.profile;

import main.api.Response;
import main.app.App;
import main.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class ShowProfile extends ActionState {

    public ShowProfile(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        Response<User> response = App.api.profile().getProfile();

        if (response.getStatus() != 200) {
            Console.println("User profile couldn't be retrieved");
            return;
        }

        User profile = response.getData();

        Console.divider();
        Console.println("ID: " + profile.getId());
        Console.println("Name: " + profile.getName());
        Console.println("E-mail: " + profile.getEmail());
        Console.println("Admin: " + (profile.getAdmin() == 0 ? "No" : "Yes"));
    }
}
