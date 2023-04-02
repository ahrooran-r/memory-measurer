package com.github.ahrooranr.util;

public class Util {
    private Util() {
    }

    public static void caption(String caption) {
        final String decorator = "========================================== ";
        System.out.printf("\n%s%s%s\n", decorator, caption, decorator);
    }
}
