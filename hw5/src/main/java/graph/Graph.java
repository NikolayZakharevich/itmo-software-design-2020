package graph;


import draw.Circle;
import draw.Point;
import draw.DrawingApi;


import java.io.BufferedReader;
import java.io.IOException;

import static java.lang.Math.*;

public abstract class Graph {
    /**
     * Bridge to drawing api
     */
    protected DrawingApi drawingApi;

    private Point centralPoint;

    private double radius;

    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    protected int vertexCount;

    public void draw() {
        centralPoint = new Point(drawingApi.getDrawingAreaWidth() / 2, drawingApi.getDrawingAreaHeight() / 2);

        long minAreaMeasure = min(drawingApi.getDrawingAreaWidth(), drawingApi.getDrawingAreaHeight());
        radius = minAreaMeasure * 0.3;
        double vertexRadius = sqrt(minAreaMeasure) / 2;

        for (int i = 0; i < vertexCount; i++) {
            drawingApi.drawCircle(new Circle(getVertexCenter(i), vertexRadius));
        }

        drawGraph();
    }

    public abstract void drawGraph();

    protected Point getVertexCenter(int i) {
        return new Point(
                (int) (centralPoint.getX() + radius * cos(2 * Math.PI * i / vertexCount)),
                (int) (centralPoint.getY() + radius * sin(2 * Math.PI * i / vertexCount))
        );
    }

    protected static int readInt(BufferedReader reader, String errorMessage) throws IOException {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

}