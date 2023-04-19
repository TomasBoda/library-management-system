package main.state.nodes.add.order;

import main.api.Response;
import main.app.App;
import main.library.model.Order;
import main.library.model.User;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;
import main.utils.Converter;

public class AddOrder extends ActionState {

    public AddOrder(String command, String message) {
        super(command, message);
    }

    public AddOrder(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String userId = getValues()[1];
        String bookId = getValues()[2];
        String createdDate = getValues()[3];
        String expirationDate = getValues()[4];

        Response response = App.api.orders().add(new Order(userId, bookId, Converter.getSqlDate(createdDate), Converter.getSqlDate(expirationDate)));
        Console.println(response.getMessage());
    }
}
