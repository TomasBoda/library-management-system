package main.state.nodes.edit.order;

import main.state.State;
import main.state.nodes.edit.user.EditUser;
import main.state.types.InputState;
import main.utils.Converter;

public class EditOrderExpirationDate extends InputState {

    public EditOrderExpirationDate(String command, String message) {
        super(command, message);
    }

    public EditOrderExpirationDate(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public String getCustomAskValue() {
        return "(" + Converter.sqlDateToString(((EditOrder) getCallback()).getOrder().getExpirationDate()) + ") ";
    }
}
