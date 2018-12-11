import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class Painter extends Thread{
	private final GraphicsContext _gc;
	
	public Painter(GraphicsContext gc) {
		_gc = gc;
	}
	
	public void run() {
		while(!interrupted()) {
			try {
				sleep(50);
			} catch (InterruptedException e) {
				System.out.println("Painter is interrupted!");
			}
			
			draw();
		}
	}
	
	protected void draw() {
		_gc.clearRect(0, 0, Main.width, Main.height);
		drawAirports();
		drawAirplanes();
		//drawInfo();
	}
	
	private void drawAirports() {
		for(Airport airport: Main.airports) {
			_gc.drawImage(Airport.getImg(), airport.getX() - Airport.getImg().getWidth()/2, airport.getY() - Airport.getImg().getHeight()/2);
			
			_gc.setFill(Color.BLUE);
			_gc.fillText(airport.getID(), airport.getX() - airport.getW()/2, airport.getY() - airport.getH()/1.5);
		}
	}
	
	private void drawAirplanes() {
		for(Airport airport: Main.airports) {
			for(Airplane airplane: airport.getAirplanes()) {
				_gc.save();
				//translate(airplane);
				//rotate(airplane.getRotationAngle(), airplane.getX() - Airplane.getImg().getWidth()/2, airplane.getY() - Airplane.getImg().getHeight()/2);
				_gc.drawImage(Airplane.getImg(), airplane.getX() - Airplane.getImg().getWidth()/2, airplane.getY() - Airplane.getImg().getHeight()/2);
				_gc.restore();
				
				_gc.setFill(Color.BLUE);
				_gc.fillText(airplane.getID(), airplane.getX() - airplane.getW()/2, airplane.getY() - airplane.getH()/1.5);
				
				drawFlightPath(airplane);
			}
		}
	}
	
	private void drawInfo() {
		_gc.setStroke(Color.GREEN);
		_gc.setLineWidth(2);
		_gc.setLineDashes(0);
		_gc.strokeRoundRect(Main.width - 300, Main.height - 500, 250, 500, 10, 10);

		for(int port = 0; port < Main.airports.size(); port++) {
			for(int plane = 0; plane < Main.airports.get(port).getAirplanes().size(); plane++) {
				_gc.fillText(Main.airports.get(port).getAirplanes().get(plane).getID(), Main.width - 280, Main.height - 480 + port*20);
			}
		}
	}
	
	private void drawFlightPath(Airplane airplane) {
		_gc.setStroke(Color.BLACK);
		_gc.setLineWidth(1);
		_gc.setLineDashes(5);
		_gc.strokeLine(airplane.getSource().getX(), airplane.getSource().getY(), airplane.getX(), airplane.getY());
	}
	
	private void translate(Airplane airplane) {
		_gc.translate(airplane.getX() - Airplane.getImg().getWidth()/2, airplane.getY() - Airplane.getImg().getHeight()/2);
	}
	
	private void rotate(double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		_gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}


}
