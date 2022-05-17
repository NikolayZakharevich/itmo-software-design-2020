package tokenizer;

import token.Token;

abstract public class State {

    protected Tokenizer tokenizer;

    public State(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    abstract void process(char ch);

    void processEof() {
        tokenizer.state = new EndState(tokenizer);
    }

    protected void createToken(Token token) {
        tokenizer.tokens.add(token);
    }

}
