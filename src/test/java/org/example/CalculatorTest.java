package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Requirements
 * Simple arithmetic operations should be supported.
 * Calculations can only be performed with positive numbers.
 * When dividing by 0, an IllegalArgumentException should be thrown.
 * Implement the application based on the Model-View-Controller (MVC) pattern.
 */

public class CalculatorTest {

    // 1 + 2 ------> Calculator
    // 3     <------
    @DisplayName("Perform addition.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int operand1, String operator, int operand2, int result) {
        int calculateResult = Calculator.calculate(operand1, operator, operand2);

        assertThat(calculateResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(2, "*", 6, 12),
                arguments(8, "/", 4, 2)
        );
    }

    @DisplayName("When dividing by 0, an IllegalArgumentException should be thrown.")
    @Test
    void calculateExceptionTest() {
        assertThatCode(() -> Calculator.calculate(9, "/", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot divide by 0.");
    }
}
