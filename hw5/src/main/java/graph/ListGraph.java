package graph;

import draw.DrawingApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListGraph extends Graph {
    final private List<Edge> edges;

    public ListGraph(DrawingApi drawingApi, int vertexCount, List<Edge> edges) {
        super(drawingApi);
        this.vertexCount = vertexCount;
        this.edges = edges;
    }

    private static class Vertex {
        final int number;

        public Vertex(int number) {
            this.number = number;
        }
    }


    private static class Edge {
        final Vertex from, to;

        public Edge(Vertex from, Vertex to) {
            this.from = from;
            this.to = to;
        }
    }

    @Override
    public void drawGraph() {
        for (Edge edge : edges) {
            drawingApi.drawLine(getVertexCenter(edge.from.number), getVertexCenter(edge.to.number));
        }
    }

    public static ListGraph readListGraph(DrawingApi drawingApi) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = readInt(reader, "Expected vertex count");
        int m = readInt(reader, "Expected edges count");

        List<Edge> edges = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {

            String str = reader.readLine();
            String[] strParts = str.trim().split(" ");
            assert strParts.length == 2 : "Expected two vertexes";

            int from, to;
            try {
                from = Integer.parseInt(strParts[0]);
                to = Integer.parseInt(strParts[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid vertex");
            }
            edges.add(new Edge(new Vertex(from - 1), new Vertex(to - 1)));
        }

        return new ListGraph(drawingApi, n, edges);
    }

}