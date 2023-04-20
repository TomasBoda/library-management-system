package main.state.nodes.admin.add.order;

import main.api.Response;
import main.app.App;
import main.model.Order;
import main.state.State;
import main.state.types.ActionState;
import main.utils.Console;
import main.utils.Converter;

import java.sql.Date;

public class AddOrder extends ActionState {

    public AddOrder(String command, String message) {
        super(command, message);
    }

    public AddOrder(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        String userId = getInputs()[1];
        String bookId = getInputs()[2];
        Date createdDate;
        Date expirationDate;

        try {
            createdDate = Converter.getSqlDate(getInputs()[3]);
            expirationDate = Converter.getSqlDate(getInputs()[3]);
        } catch (Exception e) {
            Console.println("Dates have incorrect format");
            return;
        }

        Response response = App.api.orders().add(new Order(userId, bookId, createdDate, expirationDate));
        Console.println(response.getMessage());
    }
}
