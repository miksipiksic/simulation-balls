package simulacija;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;

public class Scena extends Canvas implements Runnable {

    private Thread thread = new Thread(this);
    private boolean pokrenuta;
    private ArrayList<Figura> figure = new ArrayList<>();
    

    public Scena() {
        addListeners();
        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.GRAY);
        thread.start();
    }
    private int p = 0;
 //   private boolean pauza = false;
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                getParent().requestFocus();
            	if(p > 0 && pokrenuta) return;

                int dugme = e.getButton();
                int x = e.getX();
                int y = e.getY();
                Figura fig = null;
                if (dugme == MouseEvent.BUTTON1) {
                    fig = new Disk(new Vektor(x, y), new Vektor());
                    System.out.println(fig.getPolozaj().getX() + " " + fig.getPolozaj().getY());
                    System.out.println(fig.getPomeraj().getX() + " " + fig.getPomeraj().getY());
                }
                else {
                    return;
                }

                for (Figura f : figure) {
                    if (f.preklapanje(fig)) {
                        return;
                    }
                }
                figure.add(fig);
                repaint();
            }

        });
    }

    @Override
    public void paint(Graphics g) {
        for (Figura fig : figure) fig.iscrtaj(g);
        if (!pokrenuta) {
        	pauza(g);
        }
    }
    
    
    private void pomeriFigure() {

        int pikseli = 3;
        for (Figura fig : figure) {
            
            if(fig.getPolozaj().getX() <= fig.getR()) {
				//sudar sa levom ivicom (x=0 + R da ne bi dotakla kruznica, y=(0, Height))
				System.out.println("sudar sa levom ivicom");
				fig.polozaj.setX(fig.getR());
				fig.pomeraj.setX(-fig.pomeraj.getX());		
				
			} else if( fig.getPolozaj().getY() <= fig.getR() ) {
				//sudar sa gornjom ivicom
				System.out.println("sudar sa gornjom ivicom");
				fig.polozaj.setY(fig.getR());
				fig.pomeraj.setY(-fig.pomeraj.getY());
			} else if (fig.getPolozaj().getX() >= this.getWidth() - fig.getR()) {
				//sudar sa desnom ivicom
				System.out.println("sudar sa desnom ivicom");
				fig.polozaj.setX(this.getWidth() - fig.getR());
				fig.pomeraj.setX(-fig.pomeraj.getX());
				
			}	else if(fig.polozaj.getY() >= this.getHeight() - fig.getR()) {
				//sudar sa donjom ivicom
				System.out.println("sudar sa donjom ivicom");
				fig.polozaj.setY(this.getHeight() - fig.getR());
				fig.pomeraj.setY(-fig.pomeraj.getY());
			}
            
            for(Figura f: figure) {
				if(fig != f) {
					
				//da li im se dodiruju kruznice
				if(f.preklapanje(fig) ) {
					//sudar sa figurom se desio -> odbijanje
					System.out.println("sudar sa figurom");
					if(f.polozaj.getX() < fig.polozaj.getX()) {
						//f iznad b2
						f.pomeraj.setY(-Math.abs(f.pomeraj.getY()));
						fig.pomeraj.setY(Math.abs(fig.pomeraj.getY()));
					} else {
						f.pomeraj.setY(Math.abs(f.pomeraj.getY()));
						fig.pomeraj.setY(-Math.abs(fig.pomeraj.getY()));
					}
					if(f.polozaj.getY() < fig.polozaj.getY()) {
						//f levo od fig
						f.pomeraj.setX(-Math.abs(f.pomeraj.getX()));
						fig.pomeraj.setX(Math.abs(fig.pomeraj.getX()));
					} else {
						f.pomeraj.setX(Math.abs(f.pomeraj.getX()));
						fig.pomeraj.setX(-Math.abs(fig.pomeraj.getX()));
					}

				}
				}
				
			}
            

            double x = fig.getPolozaj().getX() + fig.getPomeraj().getX()*pikseli;
            double y = fig.getPolozaj().getY() + fig.getPomeraj().getY()*pikseli;
         
            fig.setPolozaj(new Vektor(x,y));

            
        }

    }

    @Override
    public void run() {
        try {
            while (!thread.isInterrupted()) {
                synchronized (this) {
                    while (!pokrenuta) {
                        wait();
                    }
                }
                repaint();
                Thread.sleep(100);
                pomeriFigure();
            }
        } catch (InterruptedException e) {}
    }

    public synchronized void nastavi() {
        pokrenuta = true;
  //      getParent().requestFocus();
   //     pauza = false;
        notify();
    }
    
    private void pauza(Graphics g) {

        String pauza = "PAUZA";
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.drawString(pauza, 
				(getWidth() - g.getFontMetrics().stringWidth(pauza)) / 2,
				(g.getFontMetrics().getAscent() + (
						getHeight() - (g.getFontMetrics().getAscent() 
	+g.getFontMetrics().getDescent()  )) / 2));
    }

    public synchronized void pauziraj() {
        pokrenuta = false;
 //       pauza = true;
        p++;
        Graphics g = this.getGraphics();
     //   this.requestFocus();
        pauza(g);
    }

    public synchronized boolean pokrenuta() {
        return pokrenuta;
    }

    public void zavrsi() {
        thread.interrupt();
    }
}
