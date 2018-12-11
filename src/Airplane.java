import java.io.File;

import javafx.scene.image.Image;

public class Airplane extends Entity {
	private Airport _sourceAirport = null;
	private Airport _targetAirport = null;
	
	private static Image _image = new Image(new File(Main.assetsPath + "/Airplane.png").toURI().toString());
	private final double _angle;
	
	private int step = 0;
	
	protected double  _velocity = 2;

	public Airplane(String id, double x, double y, double w, double h, Airport source, Airport target) {
		super(id, x, y, w, h);
		_sourceAirport = source;
		_targetAirport = target;
		_angle = Calculator.getAngleBetween(source, target);
	}
	
	public void run() {
		while(!interrupted()) {
			try {
				sleep(50);
				processCoords();
			} catch (InterruptedException e) {
				System.out.println("Thread of plane: " + this.getID() + " is interrupted.");
			}
		}
		_sourceAirport.removeFromList(this);
	}
	
	protected static Image getImg() {
		return _image;
	}
	
	protected double getRotationAngle() {
		return _angle;
	}
	
	protected Airport getSource() {
		return _sourceAirport;
	}
	
	protected Airport getTarget() {
		return _targetAirport;
	}
	
	private void setPos(double x, double y) {
		_x = x;
		_y = y;
	}
	
	private void processCoords() {
		double planeDistance = Calculator.getDistance(this, _targetAirport);
		
		double n = (_velocity)*step;
		double x = Calculator.interpolate(_sourceAirport, _targetAirport, n)[0];
		double y = Calculator.interpolate(_sourceAirport, _targetAirport, n)[1];
		setPos(x,y);
		
		if(planeDistance > 5) {
			step++;
		} else {
			this._sourceAirport.landPlane(this);
		}
	}

}
