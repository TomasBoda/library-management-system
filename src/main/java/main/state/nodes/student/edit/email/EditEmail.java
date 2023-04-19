package main.state.nodes.student.edit.email;

import main.api.Response;
import main.app.App;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class EditEmail extends ActionState {

    public EditEmail(String command, String message) {
        super(command, message);
    }

    public EditEmail(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String email = getValues()[1];

        Response response = App.api.profile().editEmail(email);
        Console.println(response.getMessage());
    }
}
