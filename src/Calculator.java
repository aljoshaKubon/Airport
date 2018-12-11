
public class Calculator {
	
	protected static double[] interpolate(Airport source, Airport target, double n) {
		double x1 = source.getX();
		double x2 = target.getX();
		double y1 = source.getY();
		double y2 = target.getY();
		
		double distance = Math.sqrt( Math.pow( (x2-x1),2) + Math.pow( (y2-y1), 2) );
		
		double x = x1 + (n/distance)*(x2 - x1);
		double y = y1 + (n/distance)*(y2 - y1);
		return new double[] {x,y};
	}
	
	protected static double getDistance(Airport source, Airport target) {
		double x1 = source.getX();
		double x2 = target.getX();
		double y1 = source.getY();
		double y2 = target.getY();
		double distance = Math.sqrt( Math.pow( (x2-x1),2) + Math.pow( (y2-y1), 2) );
		
		return distance;
	}
	
	protected static double getDistance(Airplane source, Airport target) {
		double x1 = source.getX();
		double x2 = target.getX();
		double y1 = source.getY();
		double y2 = target.getY();
		double distance = Math.sqrt( Math.pow( (x2-x1),2) + Math.pow( (y2-y1), 2) );
		
		return distance;
	}
	
	protected static double getAngleBetween(Airport source, Airport target) {
		double srcX = source.getX() - Airport.getImg().getWidth()/2;
		double srcY = source.getY() - Airport.getImg().getHeight()/2;
		double tarX = target.getX() - Airport.getImg().getWidth()/2;
		double tarY = target.getY() - Airport.getImg().getWidth()/2;
	    double angle = 90 +  Math.toDegrees(Math.atan2(tarY - srcY, tarX - srcX));

	    if(angle < 0){
	        angle += 360;
	    }

	    return angle;
	}
}
