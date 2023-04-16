package main.state.nodes.list.book;

import main.api.Response;
import main.app.App;
import main.library.model.Book;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class ListBook extends ActionState {

    public ListBook(String command, String message) {
        super(command, message);
    }

    public ListBook(String command, String message, State callback) {
        super(command, message, callback);
    }

    public ListBook(String command, String message, State[] children) {
        super(command, message, children);
    }

    @Override
    public void execute() {
        Response<Book[]> response = App.api.books().getAll();
        Book[] books = response.getData();

        Console.println("Total entries: " + books.length);
        for (Book book : books) {
            Console.println(book.getId() + " | " + book.getTitle() + " | " + book.getDescription() + " | " + book.getAuthor());
        }
    }
}
