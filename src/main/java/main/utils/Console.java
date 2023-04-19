package main.utils;

public class Console {

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void error(String message) {
        System.err.println(message);
    }

    public static void divider() {
        System.out.println("-----------------------------------------------------------------------");
    }
}
