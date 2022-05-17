package draw;

public interface DrawingApi {

    long getDrawingAreaWidth();

    long getDrawingAreaHeight();

    void drawCircle(Circle circle);

    void drawLine(Point p1, Point p2);

}