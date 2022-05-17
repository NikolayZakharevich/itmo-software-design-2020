package draw;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class AwtDrawingApi implements DrawingApi {

    private final Graphics2D ga;
    private final int drawingAreaWidth;
    private final int drawingAreaHeight;

    public AwtDrawingApi(Graphics2D ga, int drawingAreaWidth, int drawingAreaHeight) {
        this.ga = ga;
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
        ga.fill(new Ellipse2D.Double(circle.center.x - circle.radius, circle.center.y - circle.radius, circle.radius * 2, circle.radius * 2));
    }

    @Override
    public void drawLine(Point p1, Point p2) {
        ga.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
    }
}
