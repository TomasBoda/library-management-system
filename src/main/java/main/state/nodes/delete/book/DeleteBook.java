package main.state.nodes.delete.book;

import main.api.Response;
import main.app.App;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class DeleteBook extends ActionState {
    public DeleteBook(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String title = getValues()[1];

        Response response = App.api.books().delete(title);

        if (response.getStatus() == 200) {
            Console.println("Book successfully deleted");
        } else {
            Console.println(response.getMessage());
        }
    }
}
