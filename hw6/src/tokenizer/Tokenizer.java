package tokenizer;

import token.Token;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    State state = new StartState(this);

    int pos = 0;

    List<Token> tokens = new ArrayList<>();

    public List<Token> tokenize(InputStream stream) throws IOException {
        assert state instanceof StartState : "Tokenizer should be in initial state to process input";

        try (Reader reader = new BufferedReader(new InputStreamReader
                (stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                processChar((char) c);
                pos++;
            }
        }

        processEOF();
        return tokens;
    }

    void processChar(char c) {
        state.process(c);
    }

    private void processEOF() {
        state.processEof();
    }


}
