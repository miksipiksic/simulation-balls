package simulacija;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Simulacija extends Frame {

    private Scena scena;

    private void zavrsi() {
        dispose();
        scena.zavrsi();
    }

    public void addComponents() {
        setLayout(new BorderLayout());
        add(scena = new Scena(), BorderLayout.CENTER);
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                zavrsi();
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ESCAPE) zavrsi();
                else if (keyCode == KeyEvent.VK_SPACE) {
                    if (scena.pokrenuta()) {
                    	scena.pauziraj();
                    }
                    else scena.nastavi();
                }
            }
        });
    }
    public Simulacija() {
        addComponents();
        addListeners();
        setTitle("Simulacija");
        setLocation(50,50);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Simulacija();
    }
}
