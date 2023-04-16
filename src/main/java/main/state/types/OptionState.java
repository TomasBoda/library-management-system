package main.state.types;

import main.state.State;
import main.utils.Console;

public abstract class OptionState extends State {

    public OptionState(String command, String message) { super(command, message); }

    public OptionState(String command, String message, State callback) { super(command, message, callback); }
    public OptionState(String command, String message, State[] children) { super(command, message, children); }

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
        Console.println("-------------------------------");
        Console.println(getBreadcrumbs());
        Console.print(getMessage() + " " + getOptions() + ": ");
    }

    private String getOptions() {
        String[] options = new String[getChildren().length];

        for (int i = 0; i < options.length; i++) {
            options[i] = getChildren()[i].getCommand();
        }

        return "(" + String.join(" | ", options) + ")";
    }
}
