package tokenizer;


import token.NumberToken;

public class NumberState extends State {

    int number;

    public NumberState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    void process(char ch) {
        if (Character.isDigit(ch)) {
            number = number * 10 + (ch - '0');
        } else {
            createToken(new NumberToken(number));
            tokenizer.state = new StartState(tokenizer);
            tokenizer.processChar(ch);
        }
    }

    @Override
    void processEof() {
        createToken(new NumberToken(number));
        super.processEof();
    }

}
