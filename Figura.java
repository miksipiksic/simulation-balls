package simulacija;

import java.awt.*;

public abstract class Figura {

    protected int r;
    protected Vektor polozaj, pomeraj;
    
    public int getR() {
		return r;
	}


	public void setR(int r) {
		this.r = r;
	}


	public Vektor getPolozaj() {
		return polozaj;
	}


	public void setPolozaj(Vektor polozaj) {
		this.polozaj = polozaj;
	}


	public Vektor getPomeraj() {
		return pomeraj;
	}


	public void setPomeraj(Vektor pomeraj) {
		this.pomeraj = pomeraj;
	}


	public Figura(Vektor polozaj, Vektor pomeraj, int r) {
		this.polozaj = polozaj;
		this.pomeraj = pomeraj;
		this.r = r;
    }
	
	public Figura(Vektor polozaj, Vektor pomeraj) {
		this.polozaj = polozaj;
		this.pomeraj = pomeraj;
		this.r = 20;
	}
	
	public abstract Color boja();

    public abstract void iscrtaj(Graphics g);

    private int rastojanje(Figura fig) {
        return (int)Math.sqrt(Math.pow(polozaj.getX() - fig.getPolozaj().getX(), 2) 
        		+ Math.pow(polozaj.getY() - fig.getPolozaj().getY(), 2));
    }

    public boolean preklapanje(Figura fig) {
        return rastojanje(fig) <= (r + fig.getR());
    }
    
    
}
