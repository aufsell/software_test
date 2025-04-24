package org.example.math;

import lombok.AllArgsConstructor;
import org.example.CsvWriter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public class Tan {

    private Cos cos;
    private Sin sin;
    public  Double calculate(Double x, Double eps) {
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

        double cosValue = cos.calculate(rounded, eps);
        double sinValue = sin.calculate(rounded, eps);


//        if (xValue > 0 && xValue < Math.PI) {
//            sinValue = sinValue * 1;
//        } else {
//            sinValue = sinValue * (-1);
//        }

        if (Math.abs(sinValue) < eps) {
            throw new ArithmeticException(" не определен при x = " + x);
        }

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveToCsv("Tan.csv", x, sinValue / cosValue);

        return sinValue / cosValue;
    }
}
