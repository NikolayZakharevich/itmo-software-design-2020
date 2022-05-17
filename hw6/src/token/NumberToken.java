package token;

import visitor.TokenVisitor;

import java.util.Objects;

public class NumberToken implements Token {

    int x;

    public NumberToken(int x) {
        this.x = x;
    }

    @Override
    public void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return String.format("NUMBER (%d)", x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumberToken)) return false;
        NumberToken that = (NumberToken) o;
        return x == that.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x);
    }

    public int getX() {
        return x;
    }
}
