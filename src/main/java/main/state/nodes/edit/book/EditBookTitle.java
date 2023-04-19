package main.state.nodes.edit.book;

import main.state.State;
import main.state.nodes.edit.user.EditUser;
import main.state.types.InputState;

public class EditBookTitle extends InputState {

    public EditBookTitle(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public String getCustomAskValue() {
        return "(" + ((EditBook) getCallback()).getBook().getTitle() + ") ";
    }
}
