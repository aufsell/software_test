package org.example.math;

import lombok.AllArgsConstructor;
import org.example.CsvWriter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public class Csc {
    private Sin sin;
    public Double calculate(Double x, Double eps) {
        if (eps <= 0.0) {
            throw new IllegalArgumentException("Epsilon must be non-negative");
        }
        if (x.isNaN() || x == Double.NEGATIVE_INFINITY || x == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("x must be a finite number");
        }
        double xValue = x % (2 * Math.PI);
        if (xValue <= -Math.PI) {
            xValue += 2 * Math.PI;
        } else if (xValue > Math.PI) {
            xValue -= 2 * Math.PI;
        }

        double rounded = new BigDecimal(xValue)
                .setScale(5, RoundingMode.HALF_UP)
                .doubleValue();

        double sinValue = sin.calculate(rounded, eps);


        if (Math.abs(sinValue) < eps) {
            throw new ArithmeticException("Косек не определен при x = " + x);
        }
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveToCsv("Csc.csv", x, 1/sinValue);
        return 1/sinValue;
    }
}