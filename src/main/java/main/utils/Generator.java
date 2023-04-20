package main.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Generator {

    /**
     * Generates a MD5 hash string from the given input string
     * @param input string used for hash generation
     * @return hash string generated from the input string
     */
    public static String getHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(input.getBytes());
            byte[] digest = md.digest();

            StringBuilder builder = new StringBuilder();
            for (byte b : digest) builder.append(String.format("%02x", b & 0xff));

            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            Console.error("Error generating a hash");
            return null;
        }
    }

    /**
     * Generates a unique ID hash string
     * The input string used for generation of the hash is calculated using the system's nano time
     * @return unique ID in form of a hash string
     */
    public static String getId() {
        String randomString = Long.toString(System.nanoTime());
        return getHash(randomString);
    }
}
