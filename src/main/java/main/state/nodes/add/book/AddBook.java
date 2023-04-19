package main.state.nodes.add.book;

import main.app.App;
import main.api.Response;
import main.library.model.Book;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class AddBook extends ActionState {

    public AddBook(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String title = getValues()[1];
        String description = getValues()[2];
        String author = getValues()[3];
        String stock = getValues()[4];

        Response response = App.api.books().add(new Book(title, description, author, Integer.parseInt(stock)));
        Console.println(response.getMessage());
    }
}
