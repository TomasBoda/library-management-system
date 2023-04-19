package main.state.nodes.admin.list.book;

import main.api.Response;
import main.app.App;
import main.library.model.Book;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class ListBook extends ActionState {

    public ListBook(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        Response<Book[]> response = App.api.books().getAll();
        Book[] books = response.getData();

        Console.divider();
        Console.println("Total entries: " + books.length);
        for (Book book : books) {
            Console.println(book.getStock() + " | " + book.getTitle() + " | " + book.getAuthor() + " | " + book.getDescription());
        }
    }
}
