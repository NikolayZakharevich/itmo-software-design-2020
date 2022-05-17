package token;

import visitor.TokenVisitor;

public class Brace implements Token {

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Brace;
    }
}
