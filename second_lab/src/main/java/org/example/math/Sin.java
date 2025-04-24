package org.example.math;
import lombok.AllArgsConstructor;
import org.example.CsvWriter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
public class Sin {
    private Cos cos;

    public Double calculate(Double x, Double eps) {
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

        double sinSquared = 1 - cosValue * cosValue;
        double sinValue;
        if (rounded >= 0 && rounded <= Math.PI) {
            sinValue = Math.sqrt(sinSquared);
        } else {
            sinValue = -Math.sqrt(sinSquared);
        }
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveToCsv("Sin.csv", x, sinValue);
        return sinValue;
    }
}
