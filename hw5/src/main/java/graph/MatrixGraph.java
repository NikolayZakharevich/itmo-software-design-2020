package graph;

import draw.DrawingApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MatrixGraph extends Graph {

    private boolean[][] matrix;

    public MatrixGraph(DrawingApi drawingApi, boolean[][] matrix) {
        super(drawingApi);
        this.matrix = matrix;
        vertexCount = matrix.length;
    }


    @Override
    public void drawGraph() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j]) {
                    drawingApi.drawLine(getVertexCenter(i), getVertexCenter(j));
                }
            }
        }
    }

    static public MatrixGraph readMatrixGraph(DrawingApi drawingApi) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = readInt(reader, "Expected vertexes count");
        final boolean[][] result = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String str = reader.readLine();

            String[] strParts = str.trim().split(" ");
            for (int j = 0; j < strParts.length; j++) {
                if (strParts[j].equals("1")) {
                    result[i][j] = true;
                } else if (!strParts[j].equals("0")) {
                    throw new IllegalArgumentException("Invalid format in ${i + 1} line of matrix");
                }
            }
        }

        return new MatrixGraph(drawingApi, result);
    }
}

