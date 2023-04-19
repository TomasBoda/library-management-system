package main.state.nodes.admin.edit.order;

import main.api.Response;
import main.app.App;
import main.model.Book;
import main.model.Order;
import main.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.state.types.InputState;
import main.utils.Console;

public class EditOrderCheckBook extends InputState {

    public EditOrderCheckBook(String command, String message) {
        super(command, message);
    }

    public EditOrderCheckBook(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public State next(String command) {
        String userEmail = ((ActionState) getCallback()).getValues()[1];
        String bookTitle = command;

        Response<User> responseUser = App.api.users().getByEmail(userEmail);

        if (responseUser.getStatus() != 200) {
            Console.println("User with the given e-mail does not exist");
            return getCallback().getCallback();
        }

        Response<Book> responseBook = App.api.books().getByTitle(bookTitle);

        if (responseBook.getStatus() != 200) {
            Console.println("Book with the given title does not exist");
            return getCallback().getCallback();
        }

        String userId = responseUser.getData().getId();
        String bookId = responseBook.getData().getId();

        Response<Order> response = App.api.orders().getById(userId, bookId);

        if (response.getStatus() == 200) {
            ((EditOrder) getCallback()).setOrder(response.getData());
            return ((ActionState) getCallback()).nextChild(command);
        }

        Console.println("Order with the given userId and bookId does not exist");
        return getCallback().getCallback();
    }
}
