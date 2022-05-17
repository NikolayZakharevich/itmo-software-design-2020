package application;

import draw.JavaFxDrawingApi;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author akirakozov
 */
public class JavaFxApplication extends Application implements application.Application {

    @Override
    public void start(Stage primaryStage) {
        int width = ApplicationParams.drawingAreaWidth;
        int height = ApplicationParams.drawingAreaHeight;

        Canvas canvas = new Canvas(width, height);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        JavaFxDrawingApi api = new JavaFxDrawingApi(gc, width, height);


        GraphDrawerBridge graphDrawerBridge = new GraphDrawerBridge(api);
        try {
            graphDrawerBridge.readAndDrawGraph(ApplicationParams.graphType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Group root = new Group();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, Color.WHITE));
        primaryStage.show();
    }

    @Override
    public void runApplication(String[] args) {
        launch(args);
    }
}