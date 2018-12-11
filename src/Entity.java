
public abstract class Entity extends Thread{
	private String _id;
	protected double _x, _y;
	private double _w, _h;
	
	public Entity(String id, double x, double y, double w, double h) {
		_id = id;
		_x = x;
		_y = y;
		_w = w;
		_h = h;
	}
	
	public void run() {
	}
	
	protected String getID() {
		return _id;
	}
	
	protected double getX() {
		return _x;
	}

	protected double getY() {
		return _y;
	}
	
	protected double getW() {
		return _w;
	}
	
	protected double getH() {
		return _h;
	}
	
	@Override
	public String toString() {
		return this.getClass().toString() + " " + this.getID();
	}
}
