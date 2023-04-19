package main.state.nodes.add.order;

import main.api.Response;
import main.app.App;
import main.library.model.Book;
import main.state.State;
import main.state.nodes.edit.book.EditBook;
import main.state.types.ActionState;
import main.state.types.InputState;
import main.utils.Console;

public class AddOrderBook extends InputState {

    public AddOrderBook(String command, String message) {
        super(command, message);
    }

    public AddOrderBook(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public State next(String command) {
        Response<Book> response = App.api.books().getByTitle(command);

        if (response.getStatus() == 200) {
            return ((ActionState) getCallback()).nextChild(response.getData().getId());
        }

        Console.println("Book with the given title does not exist, try again");
        return getCallback().getCallback();
    }
}
