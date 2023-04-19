package main.state.nodes.edit.order;

import main.api.Response;
import main.app.App;
import main.library.model.Order;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;
import main.utils.Converter;

import java.sql.Date;

public class EditOrder extends ActionState {

    private Order order;

    public EditOrder(String command, String message) {
        super(command, message);
    }

    public EditOrder(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String userId = getOrder().getUserId();
        String bookId = getOrder().getBookId();
        Date createdDate = getOrder().getCreatedDate();
        String newExpirationDate = getValues()[3];

        Response response = App.api.orders().edit(userId, bookId, new Order(userId, bookId, createdDate, Converter.getSqlDate(newExpirationDate)));
        Console.println(response.getMessage());
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
