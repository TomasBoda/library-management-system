package main.app;

import java.util.Arrays;

public class Configuration {

    private static final int MAJOR_VERSION = 1;
    private static final int MINOR_VERSION = 0;
    private static final int BUGFIX_VERSION = 0;

    public static final String VERSION = MAJOR_VERSION + "." + MINOR_VERSION + "." + BUGFIX_VERSION;

    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "";
}
