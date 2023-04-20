package main.state.nodes.admin.edit.book;

import main.api.Response;
import main.app.App;
import main.model.Book;
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
        String newTitle = getInputs()[2];
        String newDescription = getInputs()[3];
        String newAuthor = getInputs()[4];
        int newStock;

        try { newStock = Integer.parseInt(getInputs()[5]); } catch (Exception e) {
            Console.println("Stock must be a number");
            return;
        }

        Response response = App.api.books().edit(book.getTitle(), new Book(newTitle, newDescription, newAuthor, newStock));
        Console.println(response.getMessage());
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
