package main.state.types;

import main.state.State;
import main.utils.Console;

public abstract class OptionState extends State {

    public OptionState(String command, String message) {
        super(command, message);
    }

    public OptionState(String command, String message, State callback) {
        super(command, message, callback);
    }

    @Override
    public State next(String command) {
        for (State child : getChildren()) {
            if (child.getCommand().equals(command)) {
                return child;
            }
        }

        Console.println("Command not found");
        return this;
    }

    @Override
    public void ask() {
        Console.divider();
        Console.println(getBreadcrumbs());
        Console.print(getMessage() + " " + getOptions() + ": ");
    }

    /**
     * Retrieves all child states of the given state
     * @return a string of child states' commands
     */
    private String getOptions() {
        String[] options = new String[getChildren().length];

        for (int i = 0; i < options.length; i++) {
            options[i] = getChildren()[i].getCommand();
        }

        return "(" + String.join(" | ", options) + ")";
    }
}
