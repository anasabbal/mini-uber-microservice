package com.nas.core.util;

public class PatternUtil {
    private PatternUtil() {
        throw new IllegalStateException();
    }

    public static String likePattern(String value) {
        if (value == null) throw new IllegalArgumentException();
        return "%" + value + "%";
    }
}