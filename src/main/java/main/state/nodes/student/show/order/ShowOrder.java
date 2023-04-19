package main.state.nodes.student.show.order;

import main.api.Response;
import main.app.App;
import main.state.State;
import main.state.nodes.student.model.OrderResult;
import main.state.types.ActionState;
import main.utils.Console;
import main.utils.Converter;

public class ShowOrder extends ActionState {

    public ShowOrder(String command, String message) {
        super(command, message);
    }

    public ShowOrder(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public void execute() {
        Console.divider();
        printActiveOrders();
        printPastOrders();
    }

    private void printActiveOrders() {
        Response<OrderResult[]> futureOrdersResponse = App.api.profile().getOrders("orders.expirationDate >= NOW()");
        OrderResult[] futureOrders = futureOrdersResponse.getData();

        Console.println("Active orders (" + futureOrders.length + ")");
        for (OrderResult order : futureOrders) {
            Console.println("   " + order.getBookTitle() + " | " + order.getBookAuthor() + " | " + Converter.sqlDateToString(order.getOrderCreatedDate()) + " | " + Converter.sqlDateToString(order.getOrderExpirationDate()));
        }
    }

    private void printPastOrders() {
        Response<OrderResult[]> pastOrdersResponse = App.api.profile().getOrders("orders.expirationDate < NOW()");
        OrderResult[] pastOrders = pastOrdersResponse.getData();

        Console.println("Past orders (" + pastOrders.length + ")");
        for (OrderResult order : pastOrders) {
            Console.println("   " + order.getBookTitle() + " | " + order.getBookAuthor() + " | " + Converter.sqlDateToString(order.getOrderCreatedDate()) + " | " + Converter.sqlDateToString(order.getOrderExpirationDate()));
        }
    }
}
