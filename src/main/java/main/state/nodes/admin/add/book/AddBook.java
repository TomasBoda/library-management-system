package main.state.nodes.admin.add.book;

import main.app.App;
import main.api.Response;
import main.model.Book;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class AddBook extends ActionState {

    public AddBook(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String title = getInputs()[1];
        String description = getInputs()[2];
        String author = getInputs()[3];
        int stock;

        try { stock = Integer.parseInt(getInputs()[4]); } catch (Exception e) {
            Console.println("Stock must be a number");
            return;
        }

        Response response = App.api.books().add(new Book(title, description, author, stock));
        Console.println(response.getMessage());
    }
}
