import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.image.Image;

public class Airport extends Entity{
	private ArrayList<Airplane> airplanes = new ArrayList<Airplane>();
	private int planeID = 0;
	
	private static Image _image = new Image(new File(Main.assetsPath + "/Airport.png").toURI().toString());
	
	
	public Airport(String id, double x, double y, double w, double h) {
		super(id, x, y, w, h);
	}
	
	public void run() {
		while(!interrupted()) {
			try {
				sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Airport: " + this.getID() + " is interrupted.");
			}
		}
	}
	
	protected static Image getImg() {
		return _image;
	}
	
	protected ArrayList<Airplane> getAirplanes(){
		return airplanes;
	}
	
	protected int getPlaneID() {
		return planeID++;
	}
	
	protected void createAirplane() {
		Airport target = getRandomAirport();
		
		Airplane tempAirplane = new Airplane(this.getID()+"->"+target.getID(), this.getX(), this.getY(), 25, 25, this, target);
		airplanes.add(tempAirplane);
		tempAirplane.start();
	}
	
	protected void createAirplane(Airplane airplane) {
		airplanes.add(airplane);
		airplane.start();
	}
	
	protected void landPlane(Airplane airplane) {
		airplane.interrupt();
	}
	
	protected void removeFromList(Airplane airplane) {
		Airport target = airplane.getTarget();
		
		for(Iterator<Airplane> iter = airplanes.iterator(); iter.hasNext();) {
			if(iter.next().equals(airplane)) {
				iter.remove();
			}
		}
		
		target.createAirplane();
	}
	
	private Airport getRandomAirport() {
		if(Main.airports.get(new Random().nextInt(Main.airports.size()-1) + 1) != this) {
			return Main.airports.get(new Random().nextInt(Main.airports.size()-1) + 1);
		} else {
			return getRandomAirport();
		}
	}

}
