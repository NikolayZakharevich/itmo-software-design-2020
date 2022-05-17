package visitor;

import token.*;

import java.util.List;
import java.util.Stack;
import java.util.function.BinaryOperator;

public class CalculatorVisitor implements TokenVisitor {

    private Stack<Integer> stack = new Stack<>();

    public int calculate(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        return stack.pop();
    }

    @Override
    public void visit(NumberToken token) {
        stack.add(token.getX());
    }

    @Override
    public void visit(Brace token) {
        throw new IllegalArgumentException("Brace is not alllowed");
    }

    @Override
    public void visit(Operation token) {
        assert stack.size() >= 2 : "Only binary operation are supported";

        int x = stack.pop();
        int y = stack.pop();
        stack.push(getOp(token).apply(y, x));
    }

    private static BinaryOperator<Integer> getOp(Operation token) {
        if (token instanceof PlusOperation) {
            return Math::addExact;
        }
        if (token instanceof MinusOperation) {
            return Math::subtractExact;
        }
        if (token instanceof MulOperation) {
            return Math::multiplyExact;
        }
        if (token instanceof DivOperation) {
            return Math::floorDiv;
        }
        return null;
    }
}
