package util;

import token.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Expressions {

    public final static String TASK_SAMPLE = "(23 + 10) * 5 – 3 * (32 + 5) * (10 – 4 * 5) + 8 / 2";
    public final static List<Token> TASK_SAMPLE_TOKENS = new ArrayList<>(Arrays.asList(
            new LeftBrace(), new NumberToken(23), new PlusOperation(), new NumberToken(10),
            new RightBrace(), new MulOperation(), new NumberToken(5), new MinusOperation(),
            new NumberToken(3), new MulOperation(), new LeftBrace(), new NumberToken(32),
            new PlusOperation(), new NumberToken(5), new RightBrace(), new MulOperation(), new LeftBrace(),
            new NumberToken(10), new MinusOperation(), new NumberToken(4), new MulOperation(),
            new NumberToken(5), new RightBrace(), new PlusOperation(), new NumberToken(8),
            new DivOperation(), new NumberToken(2)
    ));
    public final static List<Token> TASK_SAMPLE_TOKENS_RPN = new ArrayList<>(Arrays.asList(
            new NumberToken(23), new NumberToken(10), new PlusOperation(),
            new NumberToken(5), new MulOperation(), new NumberToken(3), new NumberToken(32),
            new NumberToken(5), new PlusOperation(), new MulOperation(), new NumberToken(10),
            new NumberToken(4), new NumberToken(5), new MulOperation(), new MinusOperation(),
            new MulOperation(), new MinusOperation(), new NumberToken(8), new NumberToken(2),
            new DivOperation(), new PlusOperation()
    ));


}
