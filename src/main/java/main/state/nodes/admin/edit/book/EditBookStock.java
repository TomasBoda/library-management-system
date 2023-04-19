package main.state.nodes.admin.edit.book;

import main.state.State;
import main.state.types.InputState;

public class EditBookStock extends InputState {

    public EditBookStock(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public String getCustomAskValue() {
        return "(" + ((EditBook) getCallback()).getBook().getStock() + ") ";
    }
}
