import java.util.Scanner;

public class ConsoleScanner extends Thread{
	private Scanner _scanner;
	
	public ConsoleScanner() {
		_scanner = new Scanner(System.in);
	}
	
	public void run() {
		while(!interrupted()) {	
			handle(_scanner.nextLine());			
		}
	}
	
	private void handle(String input) {
		String[] stringParts = input.split(" ");
		Airport source = null;
		Airport target = null;

		for(Airport airport: Main.airports) {
			if(airport.getID().equals(stringParts[0])) {
				source = airport;
			}else if(airport.getID().equals(stringParts[1])) {
				target = airport;
			}
		}
		
		if( (source != null) && (target != null) ) {
			source.createAirplane(new Airplane(source.getID()+"->"+target.getID(), source.getX(), source.getY(), 25, 25, source, target));
		}
	}

}
