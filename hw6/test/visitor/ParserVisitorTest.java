package visitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.Expressions.*;

public class ParserVisitorTest {

    private ParserVisitor parser;

    @BeforeEach
    void setUp() {
        parser = new ParserVisitor();
    }

    @Test
    void taskSample() {
        assertEquals(TASK_SAMPLE_TOKENS_RPN, parser.parse(TASK_SAMPLE_TOKENS));
    }

}
