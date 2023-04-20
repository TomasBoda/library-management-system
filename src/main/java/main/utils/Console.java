package main.utils;

public class Console {

    /**
     * Prints a message to the console without a newline character
     * @param message text to be printed to the console
     */
    public static void print(String message) {
        System.out.print(message);
    }

    /**
     * Prints a message to the console with a newline character
     * @param message text to be printed to the console
     */
    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Prints an error message to the console with a newline character
     * @param message error text to be printed to the console
     */
    public static void error(String message) {
        System.err.println(message);
    }

    /**
     * Prints a divider to the console with a newline character
     */
    public static void divider() {
        System.out.println("-----------------------------------------------------------------------");
    }
}
