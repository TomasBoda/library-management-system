package main.state.nodes.admin.delete.book;

import main.api.Response;
import main.app.App;
import main.library.model.Book;
import main.state.State;
import main.state.types.ActionState;
import main.state.types.InputState;
import main.utils.Console;

public class DeleteBookCheckTitle extends InputState {

    public DeleteBookCheckTitle(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public State next(String command) {
        Response<Book> response = App.api.books().getByTitle(command);

        if (response.getStatus() == 200) {
            return ((ActionState) getCallback()).nextChild(command);
        }

        Console.println("Book with the given title does not exist");
        return getCallback().getCallback();
    }
}
