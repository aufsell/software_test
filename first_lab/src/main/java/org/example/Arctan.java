package org.example;

public class Arctan {

    public static double arctan(double x, int terms) {

        if (Math.abs(x) > 1) {
            throw new IllegalArgumentException("|x| must be <= 1");
        }
        if (terms < 1) {
            throw new IllegalArgumentException("|terms| must be >= 1");
        }

        double result = 0.0;
        for (int n = 0; n < terms; n++) {
            double term = Math.pow(-1, n) * Math.pow(x, 2 * n + 1) / (2 * n + 1);
            result += term;
        }
        return result;
    }
}
