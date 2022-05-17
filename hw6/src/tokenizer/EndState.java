package tokenizer;

public class EndState extends State {

    public EndState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    void process(char ch) {
        throw new UnsupportedOperationException("Invalid state for processing");
    }

    @Override
    void processEof() {
    }

}
