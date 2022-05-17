package application;

import draw.DrawingApi;
import graph.Graph;
import graph.ListGraph;
import graph.MatrixGraph;

import java.io.IOException;

public class GraphDrawerBridge {

    private final DrawingApi drawingApi;

    public GraphDrawerBridge(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    public void readAndDrawGraph(GraphType graphType) throws IOException {
        Graph graph = null;
        switch (graphType) {
            case LIST:
                graph = ListGraph.readListGraph(drawingApi);
                break;
            case MATRIX:
                graph = MatrixGraph.readMatrixGraph(drawingApi);
                break;
        }
        graph.draw();
    }
}
