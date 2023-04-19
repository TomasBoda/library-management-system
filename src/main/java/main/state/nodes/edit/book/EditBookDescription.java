package main.state.nodes.edit.book;

import main.state.State;
import main.state.types.InputState;

public class EditBookDescription extends InputState {

    public EditBookDescription(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public String getCustomAskValue() {
        return "(" + ((EditBook) getCallback()).getBook().getDescription() + ") ";
    }
}
