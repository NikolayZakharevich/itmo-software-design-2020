package draw;

import javafx.scene.canvas.GraphicsContext;

public class JavaFxDrawingApi implements DrawingApi {

    private final GraphicsContext gc;
    private final int drawingAreaWidth;
    private final int drawingAreaHeight;

    public JavaFxDrawingApi(GraphicsContext gc, int drawingAreaWidth, int drawingAreaHeight) {
        this.gc = gc;
        this.drawingAreaWidth = drawingAreaWidth;
        this.drawingAreaHeight = drawingAreaHeight;
    }

    @Override
    public long getDrawingAreaWidth() {
        return drawingAreaWidth;
    }

    @Override
    public long getDrawingAreaHeight() {
        return drawingAreaHeight;
    }

    @Override
    public void drawCircle(Circle circle) {
        gc.fillOval(
                circle.center.x - circle.radius,
                circle.center.y - circle.radius,
                circle.radius * 2,
                circle.radius * 2
        );
    }

    @Override
    public void drawLine(Point p1, Point p2) {
        gc.strokeLine(p1.x, p1.y, p2.x, p2.y);
    }
}
