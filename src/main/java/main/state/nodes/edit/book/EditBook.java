package main.state.nodes.edit.book;

import main.api.Response;
import main.app.App;
import main.library.model.Book;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;

public class EditBook extends ActionState {

    private Book book;

    public EditBook(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String newTitle = getValues()[2];
        String newDescription = getValues()[3];
        String newAuthor = getValues()[4];
        String newStock = getValues()[5];

        Response response = App.api.books().edit(book.getTitle(), new Book(newTitle, newDescription, newAuthor, Integer.parseInt(newStock)));
        Console.println(response.getMessage());
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
