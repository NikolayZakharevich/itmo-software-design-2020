package visitor;

import token.Brace;
import token.NumberToken;
import token.Operation;

public interface TokenVisitor {
    void visit(NumberToken token);
    void visit(Brace token);
    void visit(Operation token);
}