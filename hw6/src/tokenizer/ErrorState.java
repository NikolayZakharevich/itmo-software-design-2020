package tokenizer;

public class ErrorState extends State {

    public ErrorState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    void process(char ch) {
        throw new IllegalArgumentException(String.format("Unexpected char `%c` at pos %d", ch, tokenizer.pos + 1));
    }

    @Override
    void processEof() {
        throw new IllegalArgumentException("Unexpected EOF");
    }

}
