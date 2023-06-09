package main.utils;

import main.app.App;

import java.sql.Date;
import java.util.Calendar;

public class Converter {

    /**
     * Converts a date string in form of D.M.YYYY to a java.sql.Date object
     * @param date string representation of a date in form of D.M.YYYY
     * @return java.sql.Date object of the given date string
     */
    public static Date getSqlDate(String date) {
        String[] parts = date.split("\\.");

        int day = 1;
        int month = 1;
        int year = 1;

        try {
            day = Integer.parseInt(parts[0]);
            month = Integer.parseInt(parts[1]) - 1;
            year = Integer.parseInt(parts[2]);
        } catch (Exception e) {
            Console.println("Invalid date format");
            App.exit();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        return new Date(calendar.getTime().getTime());
    }

    /**
     * Converts a java.sql.Date object to a date string in form of D.M.YYYY
     * @param date java.sql.Date object
     * @return date string in form of D.M.YYYY
     */
    public static String sqlDateToString(Date date) {
        String[] parts = date.toString().split("\\-");

        String day = parts[2];
        String month = parts[1];
        String year = parts[0];

        return day + "." + month + "." + year;
    }
}
