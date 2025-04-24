import org.example.math.*;
import org.apache.commons.csv.CSVFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import java.io.FileReader;
import java.util.Map;

public class IntegrationTest {
    private static final double EPS = 0.0001;
    private static final double funEps = 0.0001;
    private static final double testEps = 0.5;

    private static Cos cosMock;
    private static Sin sinMock;
    private static Cot cotMock;
    private static Csc cscMock;
    private static Tan tanMock;
    private static Ln lnMock;
    private static Log logMock;


    private static final Map<Object, String> mockMap = Map.of(
            cotMock = Mockito.mock(Cot.class), "/Users/xax777/Documents/itmo/software_test/second_lab/src/test/resources/mocks/CotMock.csv",
            sinMock = Mockito.mock(Sin.class), "/Users/xax777/Documents/itmo/software_test/second_lab/src/test/resources/mocks/SinMock.csv",
            cosMock = Mockito.mock(Cos.class), "/Users/xax777/Documents/itmo/software_test/second_lab/src/test/resources/mocks/CosMock.csv",
            cscMock = Mockito.mock(Csc.class), "/Users/xax777/Documents/itmo/software_test/second_lab/src/test/resources/mocks/CscMock.csv",
            tanMock = Mockito.mock(Tan.class), "/Users/xax777/Documents/itmo/software_test/second_lab/src/test/resources/mocks/TanMock.csv",
            logMock = Mockito.mock(Log.class), "/Users/xax777/Documents/itmo/software_test/second_lab/src/test/resources/mocks/LogMock.csv",
            lnMock = Mockito.mock(Ln.class), "/Users/xax777/Documents/itmo/software_test/second_lab/src/test/resources/mocks/LnMock.csv"
    );

    @BeforeAll
    static void init() throws Exception {
        for (var entry : mockMap.entrySet()) {
            loadMockData(entry.getKey(), entry.getValue());
        }
    }

    private static void loadMockData(Object mock, String filePath) throws Exception {
        try (FileReader reader = new FileReader(filePath)) {
            var records = CSVFormat.DEFAULT.parse(reader);
            for (var record : records) {
                double x = Double.parseDouble(record.get(0));
                if (mock instanceof Log) {
                    double base = Double.parseDouble(record.get(1));
                    double result = Double.parseDouble(record.get(2));
                    Mockito.when(((Log) mock).calculate(x, base, EPS)).thenReturn(result);
                } else {
                    double result = Double.parseDouble(record.get(1));
                    if (mock instanceof Cot) Mockito.when(((Cot) mock).calculate(x, EPS)).thenReturn(result);
                    else if (mock instanceof Cos) Mockito.when(((Cos) mock).calculate(x, EPS)).thenReturn(result);
                    else if (mock instanceof Csc) Mockito.when(((Csc) mock).calculate(x, EPS)).thenReturn(result);
                    else if (mock instanceof Tan) Mockito.when(((Tan) mock).calculate(x, EPS)).thenReturn(result);
                    else if (mock instanceof Ln)  Mockito.when(((Ln) mock).calculate(x, EPS)).thenReturn(result);
                    else if (mock instanceof Sin) Mockito.when(((Sin) mock).calculate(x, EPS)).thenReturn(result);
                }
            }
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/result.csv")
    void testTargetMocks(Double input, Double expected) {
        ResultFunction target = new ResultFunction(cosMock, cotMock, cscMock, sinMock, tanMock, logMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/result.csv")
    void testCotMocks(Double input, Double expected) {
        Cot cot = new Cot(cosMock,sinMock);
        ResultFunction target = new ResultFunction(cosMock,cot,cscMock,sinMock,tanMock,logMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/result.csv")
    void testTanMocks(Double input, Double expected) {
        Tan tan = new Tan(cosMock,sinMock);
        ResultFunction target = new ResultFunction(cosMock,cotMock,cscMock,sinMock,tan,logMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/result.csv")
    void testCscMocks(Double input, Double expected) {
        Csc csc = new Csc(sinMock);
        ResultFunction target = new ResultFunction(cosMock,cotMock,csc,sinMock,tanMock,logMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/result.csv")
    void testSinMocks(Double input, Double expected) {
        Cos cos = new Cos();
        Sin sin1 = new Sin(cos);
        Sin sin = new Sin(cosMock);
        Cot cot = new Cot(cosMock,sin);
        Csc csc = new Csc(sin);
        Tan tan = new Tan(cosMock,sin);
        ResultFunction target = new ResultFunction(cosMock,cot,csc,sin,tan,logMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/result.csv")
    void testCosMocks(Double input, Double expected) {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Cot cot = new Cot(cos,sin);
        Csc csc = new Csc(sin);
        Tan tan = new Tan(cos,sin);
        ResultFunction target = new ResultFunction(cos,cot,csc,sin,tan,logMock);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/result.csv")
    void testLogMock(Double input, Double expected) {
        Log log = new Log(lnMock);
        ResultFunction target = new ResultFunction(cosMock, cotMock, cscMock, sinMock,tanMock, log);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/result.csv")
    void testLnMock(Double input, Double expected) {
        Ln ln = new Ln();
        Log log = new Log(ln);
        ResultFunction target = new ResultFunction(cosMock, cotMock, cscMock, sinMock,tanMock, log);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/result.csv")
    void testNoMocks(Double input, Double expected) {
        Cos cos = new Cos();
        Sin sin = new Sin(cos);
        Cot cot = new Cot(cos,sin);
        Csc csc = new Csc(sin);
        Tan tan = new Tan(cos,sin);
        Ln ln = new Ln();
        Log log = new Log(ln);
        ResultFunction target = new ResultFunction(cos,cot,csc,sin,tan,log);
        Double result = target.calculate(input, funEps);
        Assertions.assertEquals(expected, result, testEps);
    }

}
