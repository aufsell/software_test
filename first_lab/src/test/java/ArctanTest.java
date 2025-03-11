import org.example.Arctan;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ArctanTest {

    private static final double INACCURACY = 0.001;
    private static final double ATAN_0_1 = 0.09966865249116204;
    private static final double ATAN_N_0_1 = -0.09966865249116204;
    private static final double ATAN_0_4 = 0.3805063771123649;
    private static final double ATAN_0_8 = 0.6747409422235527;
    private static final double ATAN_1 = 0.7853981633974483;

    @Test
    void testArctanOutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Arctan.arctan(1.1, 10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Arctan.arctan(-1.1, 10));
    }
    @Test
    void testArctanBasicValues() {
        Assertions.assertEquals(0, Arctan.arctan(0.0, 10), INACCURACY);
        Assertions.assertEquals(ATAN_1, Arctan.arctan(1.0, 300000), INACCURACY);
    }

    @ParameterizedTest
    @MethodSource("arctanArguments")
    void testArctanAccuracy(double input, double expected) {
        double result = Arctan.arctan(input, 10);
        Assertions.assertTrue(Math.abs(result - expected) < INACCURACY,
                "Expected: " + expected + ", but got: " + result);
    }

    static Stream<Arguments> arctanArguments() {
        return Stream.of(
                Arguments.of(0.1, ATAN_0_1),
                Arguments.of(-0.1, ATAN_N_0_1),
                Arguments.of(0.4, ATAN_0_4),
                Arguments.of(0.8, ATAN_0_8)
        );
    }
}
