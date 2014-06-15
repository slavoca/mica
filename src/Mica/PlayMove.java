/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mica;

/**
 * Vizualno pomjeranje figura
 * @author Slavica
 */
public class PlayMove implements Runnable{
    public Figure sourceFigure;
    public Figure destinationFigure;
    public Thread thread;

    public PlayMove(Figure sourceFigure,Figure destinationFigure) {
        this.sourceFigure = sourceFigure;
        this.destinationFigure=destinationFigure;
        thread=new Thread(this);
        
    }

    
    
  
    /**
     * Kretanje figura
     */
    @Override
    public void run() {
        
        int sleapTime=1;
        float k;
        float n;
        
        int dx;
        int dy;
        if(sourceFigure.getX()>destinationFigure.getX())
            dx=-1;
        else
            dx=1;
        if(sourceFigure.getY()>destinationFigure.getY())
            dy=-1;
        else
            dy=1;
        k=(float)(destinationFigure.getY()-sourceFigure.getY())/(destinationFigure.getX()-sourceFigure.getX());
        n=sourceFigure.getY()-k*sourceFigure.getX();
        if(sourceFigure.getX()!=destinationFigure.getX()+(destinationFigure.getWidth()/2)-(sourceFigure.getWidth()/2)){
        do{
           
           sourceFigure.setX(sourceFigure.getX()+dx);
          
           sourceFigure.setY((int)(k*sourceFigure.getX()+n));
          
           if(dx==1&&sourceFigure.getX()>=(destinationFigure.getX()+(destinationFigure.getWidth()/2)-(sourceFigure.getWidth()/2))){
              
               break;
           }
           if(dx==-1&&sourceFigure.getX()<=(destinationFigure.getX()+(destinationFigure.getWidth()/2)-(sourceFigure.getWidth()/2)))
           {
            
           break;
           }
           sourceFigure.setPosition();
            try {
                Thread.sleep(sleapTime);
            } catch (InterruptedException ex) {
                
            }
       }while(true);
       }
       else{
           do{
           sourceFigure.setY(sourceFigure.getY()+dy);
           if(dy==1&&sourceFigure.getY()>=(destinationFigure.getY()+(destinationFigure.getHeight()/2)-(sourceFigure.getHeight()/2)))
               break;
           if(dy==-1&&sourceFigure.getY()<=(destinationFigure.getY()+(destinationFigure.getHeight()/2)-(sourceFigure.getHeight()/2)))
               break;
           sourceFigure.setPosition();
            try {
                Thread.sleep(sleapTime);
            } catch (InterruptedException ex) {
                
            }
       }while(true);
       }
        destinationFigure.setVisible(false);
        sourceFigure.setX(destinationFigure.getX()+(destinationFigure.getWidth()/2)-(sourceFigure.getWidth()/2));
        sourceFigure.setY(destinationFigure.getY()+(destinationFigure.getHeight()/2)-(sourceFigure.getHeight()/2));
       
        
    }
    
}
