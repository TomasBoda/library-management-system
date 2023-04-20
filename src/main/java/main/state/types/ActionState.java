package main.state.types;

import main.state.State;
import main.utils.Console;

public abstract class ActionState extends State {

    private int currentStateIndex = 0;
    private String[] inputs;

    public ActionState(String command, String message) {
        super(command, message);
    }

    public ActionState(String command, String message, State callback) {
        super(command, message, callback);
    }

    public abstract void execute();

    @Override
    public State next(String command) {
        if (currentStateIndex == 0) {
            return nextChild(command);
        }

        return getChildren()[currentStateIndex].next(command);
    }

    /**
     * Moves to the next input state or to the state's callback when the action has been performed
     * @param command user input
     * @return new state to go to
     */
    public State nextChild(String command) {
        if (inputs == null) {
            execute();
            currentStateIndex = 0;
            return getCallback();
        }

        inputs[currentStateIndex] = command;

        if (isAtEnd()) {
            execute();
            currentStateIndex = 0;
            return getCallback();
        }

        return getChildren()[currentStateIndex++];
    }

    @Override
    public void ask() {
        Console.println(getBreadcrumbs());
        Console.print(getMessage() + ": ");
        currentStateIndex = 0;
    }

    @Override
    public void setChildren(State... states) {
        super.setChildren(states);
        inputs = new String[getChildren().length + 1];
    }

    public String[] getInputs() {
        return inputs;
    }

    /**
     * Checks whether all input states have been reached
     * @return information whether the state has obtained all user inputs or not
     */
    private boolean isAtEnd() {
        return currentStateIndex == getChildren().length;
    }
}
