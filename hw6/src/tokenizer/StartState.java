package tokenizer;

import token.*;

public class StartState extends State {

    public StartState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    void process(char ch) {
        switch (ch) {
            case '(':
                createToken(new LeftBrace());
                break;
            case ')':
                createToken(new RightBrace());
                break;
            case '+':
                createToken(new PlusOperation());
                break;
            case '–':
                createToken(new MinusOperation());
                break;
            case '*':
                createToken(new MulOperation());
                break;
            case '/':
                createToken(new DivOperation());
                break;
            default:
                if (Character.isDigit(ch)) {
                    tokenizer.state = new NumberState(tokenizer);
                    tokenizer.processChar(ch);
                } else if (!Character.isWhitespace(ch)) {
                    tokenizer.state = new ErrorState(tokenizer);
                    tokenizer.processChar(ch);
                }
        }
    }
}
