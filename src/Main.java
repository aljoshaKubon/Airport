import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application{
	protected static final double width = Screen.getPrimary().getVisualBounds().getWidth();
	protected static final double height = Screen.getPrimary().getVisualBounds().getHeight();
	protected static ArrayList<Airport> airports;
	
	protected static final String assetsPath = new File((new File("").getAbsolutePath() + "/assets")).getAbsolutePath();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		initialize();
		
		Canvas canvas = new Canvas(width, height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Painter painter = new Painter(gc);
		painter.start();
		
		Group root = new Group();
		root.getChildren().add(canvas);
		
		stage.setScene(new Scene(root));
		stage.setTitle("Flughafen-Simulation");
		stage.setFullScreen(true);
		stage.show();
		
		stage.setOnCloseRequest(event -> {
			System.exit(0);
		});
	}
	
	private void initialize() {
		airports = new ArrayList<Airport>();
		XMLManager xmlManager = new XMLManager(assetsPath  + "/Data.xml");
		xmlManager.loadAirports(airports);
		
		for(Airport airport: airports) {
			airport.start();
			airport.createAirplane();
		}
	}
}
