package com.url.shortner.service;

public class Base62 {
    private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encode(long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.insert(0, CHARACTERS.charAt((int) (value % 62)));
            value /= 62;
        }
        return sb.toString();
    }

    public static long decode(String str) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = result * 62 + CHARACTERS.indexOf(str.charAt(i));
        }
        return result;
    }
}