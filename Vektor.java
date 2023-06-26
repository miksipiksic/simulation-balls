package simulacija;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Vektor {
	
	private double x;
	private double y; //koordinate

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Vektor(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vektor () {
		double x, y;
		do {
			Random r = new Random();
			x = ThreadLocalRandom.current().nextDouble(-1,1);
	//		x = -1 + (1 - (-1)) + r.nextDouble();
		//	x = -1 + (1 - (-1)) + new Random().nextDouble();
			System.out.println(x);
			y = ThreadLocalRandom.current().nextDouble(-1,1);
		//	y = -1 + (1 - (-1)) + r.nextDouble();
	//		y = -1 + (1 - (-1)) + new Random().nextDouble();
			System.out.println(y);
		} while ( x == 0 && y == 0) ;
		this.x = x;
		this.y = y;
		System.out.println("kreiran vektor");
	}
	
	public Vektor ort() {
		double magnituda = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return new Vektor(x/magnituda, y/magnituda);
	}
	
	public static void main(String[] args) {
		while(true) {
			Vektor v = new Vektor();
			System.out.println(v.x + ", " + v.y);
		}
	}
	
}
