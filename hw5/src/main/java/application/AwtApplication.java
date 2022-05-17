package application;

import draw.AwtDrawingApi;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class AwtApplication extends Frame implements Application {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D ga = (Graphics2D) g;

        int width = ApplicationParams.drawingAreaWidth;
        int height = ApplicationParams.drawingAreaHeight;

        ga.clearRect(0, 0, width, height);
        AwtDrawingApi api = new AwtDrawingApi(ga, width, height);
        GraphDrawerBridge drawer = new GraphDrawerBridge(api);
        try {
            drawer.readAndDrawGraph(ApplicationParams.graphType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void runApplication(String[] args) {
        Frame frame = new AwtApplication();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        frame.setSize(ApplicationParams.drawingAreaWidth, ApplicationParams.drawingAreaHeight);
        frame.setVisible(true);
    }
}