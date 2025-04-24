package org.example;
import org.example.math.*;

public class Main {

    public static void main(String[] args) {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Cot cot = new Cot(cos,sin);
        Csc csc = new Csc(sin);
        Tan tan = new Tan(cos,sin);
        Ln ln = new Ln();
        Log log = new Log(ln);

        ResultFunction target = new ResultFunction(cos, cot,csc,sin,tan,log);
        Double[][] data2 = {{-1.2, 0.0001}, {-0.7, 0.0001}, {-2.2, 0.0001}, {-2.5, 0.0001},
                {-2.34, 0.0001}, {-3.7, 0.0001}, {-4.0, 0.0001}, {-5.3, 0.0001},{-5.66, 0.0001},
                {-5.9, 0.0001}, {0.15, 0.0001}, {0.1955, 0.0001}, {1.4, 0.0001}, {2.3, 0.0001}};
        for (Double[] value : data2) {
            try {
                target.calculateAndSaveCsv(value[0], value[1]);
            } catch (Exception e) {
                System.out.println("Error: Target.calculate(" + value[0] + ", " + value[1] + "): " + e.getMessage());
            }
        }

    }
}