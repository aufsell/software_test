package org.example.math;

import lombok.AllArgsConstructor;
import org.example.CsvWriter;

@AllArgsConstructor
public class ResultFunction {
    private Cos cos;
    private Cot cot;
    private Csc csc;
    private Sin sin;
    private Tan tan;
    private Log log;

    public Double calculate(Double x, Double eps) {
        if (eps <= 0.0) {
            throw new IllegalArgumentException("eps must be greater than zero");
        }

        if (x <= 0){
            return (((((cot.calculate(x, eps) + csc.calculate(x, eps)) * cos.calculate(x, eps)) +
                    cos.calculate(x, eps)) +
                    sin.calculate(x, eps)) -
                    (tan.calculate(x, eps) +
                            Math.pow((sin.calculate(x, eps) - csc.calculate(x, eps)) +
                                    Math.pow(sin.calculate(x, eps), 3), 2)));
        }
        else{
            double log2 = log.calculate(x, 2.0, eps);
            double log5 = log.calculate(x, 5.0, eps);
            double log10 = log.calculate(x, 10.0, eps);
            return Math.pow(Math.pow((log2 * log10 + log5), 3), 3) + log10;
        }
    }

    public void calculateAndSaveCsv(Double x, Double eps) {
        double result = calculate(x, eps);
        System.out.println("Result: " + result + "x: " + x);
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveToCsv("ResultFunction.csv", x, result);
    }
}
