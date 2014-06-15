
package Mica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Slavica
 */
class Board extends JPanel implements Runnable {
    
    //podesimo sirinu prozora
    public final int PANEL_WIDTH = 800;
    
    //podesimo visinu prozora
    public final int PANEL_HEIGHT = 600;
    
    
    //podesimo boju pozadine
    final Color BACKGROUND_COLOR =new Color(255, 255, 204);
    
    //da li je zapoceta nova igra
    private Boolean inGame=false;
    public final Thread runner;
    
    //slika pozadine
    private Image image;
    //poruka koju ispisujemo na ekranu u zavisnosti od poteza igraca
    private String message="";

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    

   
    public Board(){
        setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);
        setFont(getFont().deriveFont(Font.BOLD, 20f));
        setDoubleBuffered(true);
        image = new ImageIcon(getClass().getResource(".\\Picture\\background_2.PNG")).getImage();
        runner = new Thread(this);
        runner.start();
       
    }
    
    /**
     * Pocetak nove igre
     */
    public void newGame(){
        //Uklonimo stanja od prethode igre
        this.removeAll();       
        inGame=true;      
        //startujemo novu igru
        Game g = new Game(this);
    }
    

    
 
     @Override
    public void paintComponent(Graphics g) {
         super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (inGame) {   
            
           g2.drawImage(image, 100, 100, PANEL_WIDTH-200, PANEL_HEIGHT-200, null);         
           g2.drawString(message, 40, 40);
     
        } else {
            int messageWidth = getFontMetrics(getFont()).stringWidth("Mica");
            g2.drawString("Mica", PANEL_WIDTH / 2 - messageWidth / 2, PANEL_HEIGHT / 2);
        }
      
            

    }
     
     
      @Override
    public void run() {
        while (true) {
           
            repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
