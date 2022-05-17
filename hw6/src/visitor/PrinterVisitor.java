package visitor;

import token.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Stack;
import java.util.function.BinaryOperator;

public class PrinterVisitor implements TokenVisitor {

    public PrinterVisitor(OutputStream output) {
        this.output = output;
    }

    private Stack<String> stack = new Stack<>();

    private OutputStream output;

    public void print(List<Token> tokens) {
        tokens.forEach(token -> token.accept(this));
        try {
            output.write(stack.pop().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(NumberToken token) {
        stack.add(String.valueOf(token.getX()));
    }

    @Override
    public void visit(Brace token) {
        throw new IllegalArgumentException("Brace is not alllowed");
    }

    @Override
    public void visit(Operation token) {
        assert stack.size() >= 2 : "Only binary operation are supported";

        String x = stack.pop();
        String y = stack.pop();
        stack.push(getOp(token).apply(y, x));
    }

    private static BinaryOperator<String> getOp(Operation token) {
        if (token instanceof PlusOperation) {
            return (x, y) -> String.format("(%s + %s)", x, y);
        }
        if (token instanceof MinusOperation) {
            return (x, y) -> String.format("(%s - %s)", x, y);
        }
        if (token instanceof MulOperation) {
            return (x, y) -> String.format("(%s * %s)", x, y);
        }
        if (token instanceof DivOperation) {
            return (x, y) -> String.format("(%s / %s)", x, y);
        }
        return null;
    }
}
