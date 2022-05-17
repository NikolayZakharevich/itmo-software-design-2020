package visitor;

import token.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {

    private Stack<Token> stack = new Stack<>();
    private List<Token> parsedTokens = new ArrayList<>();

    public List<Token> parse(List<Token> tokens) {

        tokens.forEach(token -> token.accept(this));

        while (!stack.empty()) {
            Token prevToken = stack.peek();
            if (prevToken instanceof Operation) {
                parsedTokens.add(prevToken);
                stack.pop();
            } else {
                throw new IllegalStateException("Invalid stack state");
            }
        }

        return parsedTokens;
    }

    @Override
    public void visit(NumberToken token) {
        parsedTokens.add(token);
    }

    @Override
    public void visit(Brace token) {
        if (token instanceof LeftBrace) {
            stack.push(token);
            return;
        }

        if (token instanceof RightBrace) {
            while (!stack.empty()) {
                Token prevToken = stack.peek();
                if (prevToken instanceof LeftBrace) {
                    stack.pop();
                    break;
                }
                if (prevToken instanceof Operation) {
                    parsedTokens.add(prevToken);
                    stack.pop();
                } else if (prevToken instanceof RightBrace || prevToken instanceof NumberToken) {
                    throw new IllegalStateException("Illegal stack state");
                }
            }
        }
    }

    @Override
    public void visit(Operation token) {
        while (!stack.empty()) {
            Token lastToken = stack.peek();
            if (lastToken instanceof Operation && priority(token) <= priority((Operation) lastToken)) {
                parsedTokens.add(lastToken);
                stack.pop();
            } else {
                break;
            }
        }
        stack.push(token);
    }

    private static int priority(Operation token) {
        if (token instanceof PlusOperation || token instanceof MinusOperation) {
            return 1;
        }
        if (token instanceof MulOperation || token instanceof DivOperation) {
            return 2;
        }
        return 0;
    }
}
