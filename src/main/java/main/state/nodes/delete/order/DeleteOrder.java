package main.state.nodes.delete.order;

import main.api.Response;
import main.app.App;
import main.library.model.Order;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;
import main.utils.Converter;

public class DeleteOrder extends ActionState {

    private Order order;

    public DeleteOrder(String command, String message) {
        super(command, message);
    }

    public DeleteOrder(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String userId = getOrder().getUserId();
        String bookId = getOrder().getBookId();

        Response response = App.api.orders().delete(userId, bookId);
        Console.println(response.getMessage());
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
