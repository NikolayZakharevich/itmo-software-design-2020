import application.*;
import org.apache.commons.cli.*;


public class Main {

    private static final String DRAW_TYPE_AWT = "awt";
    private static final String DRAW_TYPE_JAVAFX = "javafx";


    public static void main(String[] args) {

        Options options = new Options();

        Option drawTypeOpt = new Option("d", "draw", true, "type of drawing api");
        drawTypeOpt.setRequired(true);
        options.addOption(drawTypeOpt);

        Option graphTypeOpt = new Option("g", "graph", true, "graph format");
        graphTypeOpt.setRequired(true);
        options.addOption(graphTypeOpt);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        String graphTypeVal = cmd.getOptionValue("graph");
        String drawTypeVal = cmd.getOptionValue("draw");

        ApplicationParams.drawingAreaWidth = 600;
        ApplicationParams.drawingAreaHeight = 400;
        ApplicationParams.graphType = GraphType.valueOf(graphTypeVal.toUpperCase());

        Application application = null;
        switch (drawTypeVal) {
            case DRAW_TYPE_AWT:
                application = new AwtApplication();
                break;
            case DRAW_TYPE_JAVAFX:
                application = new JavaFxApplication();
                break;
            default:
                System.err.println("Unknown draw type");
                System.exit(1);
        }
        application.runApplication(args);
    }

}

//List:
//8
//8
//1 5
//2 3
//3 4
//4 1
//2 6
//4 7
//7 8
//8 4

//Matrix:
//8
//0 0 0 1 1 0 0 0
//0 0 1 0 0 1 0 0
//0 1 0 1 0 0 0 0
//1 0 1 0 0 0 1 1
//1 0 0 0 0 0 0 0
//0 1 0 0 0 0 0 0
//0 0 0 1 0 0 0 1
//0 0 0 1 0 0 1 0
