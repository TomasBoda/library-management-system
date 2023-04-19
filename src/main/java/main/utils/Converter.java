package main.utils;

import main.app.App;

import java.sql.Date;
import java.util.Calendar;

public class Converter {

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

    public static String sqlDateToString(Date date) {
        String[] parts = date.toString().split("\\-");

        String day = parts[2];
        String month = parts[1];
        String year = parts[0];

        return day + "." + month + "." + year;
    }
}
