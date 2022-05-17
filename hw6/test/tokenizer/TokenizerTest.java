package tokenizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import token.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static util.Expressions.TASK_SAMPLE;
import static util.Expressions.TASK_SAMPLE_TOKENS;

public class TokenizerTest {

    private Tokenizer tokenizer;

    @BeforeEach
    void setUp() {
        tokenizer = new Tokenizer();
    }

    @Test
    void taskSample() throws IOException {
        assertEquals(TASK_SAMPLE_TOKENS, tokenizer.tokenize(new ByteArrayInputStream(TASK_SAMPLE.getBytes())));
    }

}
