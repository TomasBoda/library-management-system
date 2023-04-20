package main.state.nodes.student.edit.name;

import main.api.Response;
import main.app.App;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class EditName extends ActionState {

    public EditName(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String name = getInputs()[1];

        Response response = App.api.profile().editName(name);
        Console.println(response.getMessage());
    }
}
