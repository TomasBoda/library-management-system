package main.state.nodes.list.user;

import main.api.Response;
import main.app.App;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class ListUser extends ActionState {

    public ListUser(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        Response<User[]> response = App.api.users().getAll();
        User[] users = response.getData();

        Console.divider();
        Console.println("Total entries: " + users.length);
        for (User user : users) {
            Console.println(user.getAdmin() + " | " + user.getName() + " | " + user.getEmail());
        }
    }
}
