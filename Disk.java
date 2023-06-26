package simulacija;

import java.awt.*;

public class Disk extends Figura {

    public Disk(Vektor polozaj, Vektor pomeraj) {
        super(polozaj, pomeraj);
    }
    
    public Disk(Vektor polozaj, Vektor pomeraj, int r) {
    	super(polozaj, pomeraj, r);
    }
    
    private int getX(double angle) {
		return (int) (this.getR() * Math.cos(angle));
	}
	
	private int getY(double angle) {
		return (int) (this.getR() * Math.sin(angle));
	}
	
	/* zvezda
	 * int midX = 500;
    int midY = 340;
    int radius[] = {118,40,90,40};
    int nPoints = 16;
    int[] X = new int[nPoints];
    int[] Y = new int[nPoints];

    for (double current=0.0; current<nPoints; current++)
    {
        int i = (int) current;
        double x = Math.cos(current*((2*Math.PI)/max))*radius[i % 4];
        double y = Math.sin(current*((2*Math.PI)/max))*radius[i % 4];

        X[i] = (int) x+midX;
        Y[i] = (int) y+midY;
    }

	 */
	
    
    

    @Override
    public void iscrtaj(Graphics g) {
    	
    	Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(boja());
		//g.translate((int)(this.getPolozaj().getX()), (int)(this.getPolozaj().getY()));
		g2d.translate(this.getPolozaj().getX(), this.getPolozaj().getY());
		
		double inc = 2*Math.PI/8;

		int x = (int)(this.getX(0));
		int y = (int)(this.getY(0));
		
		int n = 8;
		int[] xT = new int[n];
		int[] yT = new int[n];
		double theta = (n % 2 == 0)? inc: -Math.PI/2;
		for(int i = 0; i < n; i++) {
			xT[i] = (int) (this.getX(theta));
			yT[i] = (int) (this.getY(theta));
			theta += inc;
		}
	
		g2d.drawPolygon(xT, yT, n);
		g2d.fillPolygon(xT, yT, n);

    }

	@Override
	public Color boja() {
		return Color.BLUE;
	}
}

