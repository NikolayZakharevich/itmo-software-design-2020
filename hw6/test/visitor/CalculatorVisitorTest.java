package visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Expressions.TASK_SAMPLE_TOKENS_RPN;

public class CalculatorVisitorTest {

    private CalculatorVisitor calculator;

    @BeforeEach
    void setUp() {
        calculator = new CalculatorVisitor();
    }

    @Test
    void taskSample() {
        assertEquals(1279, calculator.calculate(TASK_SAMPLE_TOKENS_RPN));
    }

}
