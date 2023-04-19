package main.utils;

import main.app.App;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Generator {

    public static String getHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(input.getBytes());
            byte[] digest = md.digest();

            StringBuilder builer = new StringBuilder();
            for (byte b : digest) builer.append(String.format("%02x", b & 0xff));

            return builer.toString();
        } catch (NoSuchAlgorithmException e) {
            Console.error("Error generating a hash");
            return null;
        }
    }

    public static String getId() {
        String randomString = Long.toString(System.nanoTime());
        return getHash(randomString);
    }
}
