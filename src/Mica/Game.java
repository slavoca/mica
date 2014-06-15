/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Klasa predstavlja simulaciju igrice
 *
 * @author Slavica
 */
public class Game {

    //Matrica na kojoj se nalaze figure na polju
    private Object[][] playerFigureMatrix = new Object[3][8];
    //Matrica koja cini polje
    private Object[][] fieldMatrix = new Object[3][8];
    private Board board;
    //Lista koja sadrzi sve plave figure
    private ArrayList<Figure> blueFigure = new ArrayList();
    //Lista koja sadrzi sve crvene figure
    private ArrayList<Figure> redFigure = new ArrayList();
    //koordinate pojedenih figurica
    private int deathBlueX = 30;
    private int deathRedX = 770;
    private int deathY = 550;
    //koji igrac je na potezu
    private int playerOnMove = 1;
    //Aktivna figura
    public Figure clicedFigure;

    public int getDeathBlueX() {
        return deathBlueX;
    }

    public int getDeathRedX() {
        return deathRedX;
    }

    public int getDeathY() {
        return deathY;
    }

    public void setDeathBlueX(int deathBlueX) {
        this.deathBlueX = deathBlueX;
    }

    public void setDeathRedX(int deathRedX) {
        this.deathRedX = deathRedX;
    }

    public Board getBoard() {
        return board;
    }

    public Game(Board board) {
        this.board = board;

        startNewGame();
    }

    ;
    
    /**
     * Pocetak igre
     * kreiranje polja i postavljanje figura
     */
    public void startNewGame() {
        createField();
        createBlueFigure();
        createRedFigure();
        addEvent();
        board.setMessage("Plavi igrač je na potezu");

    }

    /**
     * Kreiranje plavih figura
     */
    private void createBlueFigure() {

        for (int i = 0; i < 9; i++) {
            Figure f = new Figure(this, 30, 90 + (i * 50), Color.BLUE, Color.GRAY, FigureType.BLUE_FIGURE, -1, -1);
            blueFigure.add(f);
        }

    }

    /**
     * Kreiranje crvenih figura
     */
    private void createRedFigure() {

        for (int i = 0; i < 9; i++) {
            Figure f = new Figure(this, 770, 90 + (i * 50), Color.RED, Color.GRAY, FigureType.RED_FIGURE, -1, -1);
            redFigure.add(f);
        }

    }

    /**
     * Postavljanje polja
     */
    private void createField() {
        //Boja polja

        Color col = new Color(51, 51, 51);

        fieldMatrix[0][0] = new Figure(this, 125, 120, col, col, FigureType.EMPTY_FIELD, 0, 0);
        fieldMatrix[0][1] = new Figure(this, 125, 300, col, col, FigureType.EMPTY_FIELD, 0, 1);
        fieldMatrix[0][2] = new Figure(this, 125, 480, col, col, FigureType.EMPTY_FIELD, 0, 2);
        fieldMatrix[0][3] = new Figure(this, 400, 480, col, col, FigureType.EMPTY_FIELD, 0, 3);
        fieldMatrix[0][4] = new Figure(this, 670, 480, col, col, FigureType.EMPTY_FIELD, 0, 4);
        fieldMatrix[0][5] = new Figure(this, 670, 300, col, col, FigureType.EMPTY_FIELD, 0, 5);
        fieldMatrix[0][6] = new Figure(this, 670, 120, col, col, FigureType.EMPTY_FIELD, 0, 6);
        fieldMatrix[0][7] = new Figure(this, 400, 120, col, col, FigureType.EMPTY_FIELD, 0, 7);

        fieldMatrix[1][0] = new Figure(this, 220, 180, col, col, FigureType.EMPTY_FIELD, 1, 0);
        fieldMatrix[1][1] = new Figure(this, 220, 300, col, col, FigureType.EMPTY_FIELD, 1, 1);
        fieldMatrix[1][2] = new Figure(this, 220, 420, col, col, FigureType.EMPTY_FIELD, 1, 2);
        fieldMatrix[1][3] = new Figure(this, 400, 420, col, col, FigureType.EMPTY_FIELD, 1, 3);
        fieldMatrix[1][4] = new Figure(this, 580, 420, col, col, FigureType.EMPTY_FIELD, 1, 4);
        fieldMatrix[1][5] = new Figure(this, 580, 300, col, col, FigureType.EMPTY_FIELD, 1, 5);
        fieldMatrix[1][6] = new Figure(this, 580, 180, col, col, FigureType.EMPTY_FIELD, 1, 6);
        fieldMatrix[1][7] = new Figure(this, 400, 180, col, col, FigureType.EMPTY_FIELD, 1, 7);

        fieldMatrix[2][0] = new Figure(this, 310, 240, col, col, FigureType.EMPTY_FIELD, 2, 0);
        fieldMatrix[2][1] = new Figure(this, 310, 300, col, col, FigureType.EMPTY_FIELD, 2, 1);
        fieldMatrix[2][2] = new Figure(this, 310, 360, col, col, FigureType.EMPTY_FIELD, 2, 2);
        fieldMatrix[2][3] = new Figure(this, 400, 360, col, col, FigureType.EMPTY_FIELD, 2, 3);
        fieldMatrix[2][4] = new Figure(this, 490, 360, col, col, FigureType.EMPTY_FIELD, 2, 4);
        fieldMatrix[2][5] = new Figure(this, 490, 300, col, col, FigureType.EMPTY_FIELD, 2, 5);
        fieldMatrix[2][6] = new Figure(this, 490, 240, col, col, FigureType.EMPTY_FIELD, 2, 6);
        fieldMatrix[2][7] = new Figure(this, 400, 240, col, col, FigureType.EMPTY_FIELD, 2, 7);

        //Dodavanje event funkcije za polje
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                Figure f = (Figure) fieldMatrix[i][j];
                f.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        Figure figure = (Figure) e.getSource();
                        Game game = figure.getGame();

                        if (null != figure) {

                            if (game.clicedFigure != null) {
                                if (game.clicedFigure.isPresed()) {
                                    if (game.move(game.clicedFigure, figure)) {

                                        game.clicedFigure.setPosition();
                                    }

                                    game.clicedFigure.setPresed(false);

                                }
                            }

                        }

                    }
                });
            }
        }

    }
    /**
     * Dodavanje akcija na figure (mouse click eventa)
     */
    private void addEvent() {

        ArrayList<Figure> list;
        if (playerOnMove == 1) {
            list = blueFigure;
        } else {
            list = redFigure;
        }

        int onFieldFigure = 0;

        for (Figure f : list) {

            if (f.isOnField()) {
                onFieldFigure++;
            }


        }

        removeAllFigureEvent();

        for (Figure f : list) {
            if ((onFieldFigure == list.size() || (!f.isOnField()))&&f.isAlive()) {
                f.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        Figure figure = (Figure) e.getSource();
                        Game game = figure.getGame();

                        if (null != figure) {
                            if (figure.isPresed()) {
                                figure.setPresed(false);
                                game.clicedFigure = null;

                            } else {
                                if (game.clicedFigure != null) {
                                    game.clicedFigure.setPresed(false);

                                }
                                figure.setPresed(true);
                                game.clicedFigure = figure;
                            }
                        }
                    }
                });
            }
        }





    }
   /**
    * Uklanjanje svih akcija sa figura
    */
    private void removeAllFigureEvent() {
        for (Figure f : redFigure) {

            for (ActionListener al : f.getActionListeners()) {
                f.removeActionListener(al);

            }
        }

        for (Figure f : blueFigure) {

            for (ActionListener al : f.getActionListeners()) {
                f.removeActionListener(al);

            }
        }
    }

    /**
     * Dodavanje akcija za uklanjanje figura protivnika
     */
    private void addEventForRemoveEnemyFigure() {

        ArrayList<Figure> list;
        if (playerOnMove == 1) {
            list = redFigure;
        } else {
            list = blueFigure;
        }
        removeAllFigureEvent();

        for (Figure f : list) {
            if (f.isAlive() && f.isOnField()) {
                f.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        Figure figure = (Figure) e.getSource();
                        Game game = figure.getGame();
                        
                        
                       
                        
                        if (null != figure) {
                           if(canRemoveFigure(figure)){
                            
                        
                            if (figure.getType() == FigureType.BLUE_FIGURE) {
                                figure.setX(game.getDeathBlueX());
                                game.setDeathBlueX(game.getDeathBlueX() + 20);
                            } else {
                                figure.setX(game.getDeathRedX());
                                game.setDeathRedX(game.getDeathRedX() - 20);
                            }

                            figure.setY(game.getDeathY());

                            figure.setAlive(false);
                            figure.setPosition();


                        
                        ((Figure) fieldMatrix[figure.getPositionOnFieldX()][figure.getPositionOnFieldY()]).setVisible(true);
                        playerFigureMatrix[figure.getPositionOnFieldX()][figure.getPositionOnFieldY()] = null;

                        if (!chackWin()) {



                            if (playerOnMove == 1) {
                                playerOnMove = 2;
                                board.setMessage("Crveni igrač je na potezu");
                            } else {
                                playerOnMove = 1;
                                board.setMessage("Plavi igrač je na potezu");
                            }

                            addEvent();
                        } else {
                            removeAllFigureEvent();
                        }
                           }else
                           {
                           
                           JOptionPane.showMessageDialog(board, "Morate izabrati figuru koja nije u nizu od 3 figure!!!", "Info: " , JOptionPane.INFORMATION_MESSAGE);
                           
                           }
                    }
                    }
                });
            }
        }



    }
    /**
     * Ispitivanje da li se figura moze smaketi (pojesti)
     * pravilo nalaze da ukoliko se figura nalazi u nizu od 3 figure
     * da se ne moze pojesti osim ako nema figure na polju koja se ne nalazi u nizu od 3 figure
     * @param f 
     */
    private Boolean canRemoveFigure(Figure f){
        
        if(chekStatus(f)){
            
            ArrayList<Figure> list;
         
            if(f.getType()==FigureType.BLUE_FIGURE)
                list=blueFigure;
            else
                list=redFigure;
            for(Figure fi:list){
                if(fi.isAlive()&&fi.isOnField()&&!chekStatus(fi))
                    return false;
            }
            
        }
        return true;
    }
    
    /**
     * Funkcija ispituje da li se odredjena figura na terenu moze kretati
     * @param figure
     * @return da li se odredjena figura na polju moze kretati
     */
    private Boolean canMove(Figure figure) {

        int x = figure.getPositionOnFieldX();
        int y = figure.getPositionOnFieldY();

        if (numberOfFigureOfActivPlayer() <= 3) {
            return true;
        }

        if (y % 2 == 0) {
            if (y == 0) {
                if (playerFigureMatrix[x][(y + 1) % 8] == null || playerFigureMatrix[x][7] == null) {
                    return true;
                }
            } else {
                if (playerFigureMatrix[x][(y + 1) % 8] == null || playerFigureMatrix[x][y - 1] == null) {
                    return true;
                }
            }
        } else {

            if (y == 0) {
                if (playerFigureMatrix[x][(y + 1) % 8] == null || playerFigureMatrix[x][7] == null) {
                    return true;
                }
            } else {
                if (playerFigureMatrix[x][(y + 1) % 8] == null || playerFigureMatrix[x][y - 1] == null) {
                    return true;
                }
            }

            if (x == 0 && playerFigureMatrix[(x + 1) % 8][y] == null) {
                return true;
            }
            if (x == 1 && (playerFigureMatrix[(x + 1) % 8][y] == null || playerFigureMatrix[x - 1][y] == null)) {
                return true;
            }
            if (x == 2 && playerFigureMatrix[x - 1][y] == null) {
                return true;
            }

        }

        return false;
    }
     /**
      * Provjera ima li pobjednika
      * @return 
      */
    private Boolean chackWin() {
        /**
         * Ukoliko protivnicki igrac ima manje od 3 figure na polju ili
         * se ne moze kretati vise ni jednom figurom na terenu proglasavamo
         * pobjedu
         */
        ArrayList<Figure> list;
        String message = "";
        if (playerOnMove == 1) {
            list = redFigure;
            message = "Plavi igrač je popjedio";
        } else {
            list = blueFigure;
            message = "Crveni igrač je popjedio";
        }
        int alliveFigure = 0;
        Boolean canMove = false;
        int onFieldFigure = 0;

        for (Figure f : list) {
            if (f.isOnField() && f.isAlive() && canMove(f)) {
                canMove = true;
            }
            if (f.isOnField()) {
                onFieldFigure++;
            }
            if (f.isAlive()) {
                alliveFigure++;
            }
        }
        if (alliveFigure <= 2 || (!canMove && onFieldFigure == 9)) {
            board.setMessage(message);
            return true;
        }

        return false;
    }

    /**
     * Metoda vraća broj živih figura aktivnog igrača
     * @return Boolean
     */
    private int numberOfFigureOfActivPlayer() {
        int retVal = 0;
        ArrayList<Figure> lista;
        if (playerOnMove == 1) {
            lista = blueFigure;
        } else {
            lista = redFigure;
        }

        for (Figure f : lista) {
            if (f.isAlive()) {
                retVal++;
            }
        }

        return retVal;

    }
     /**
      * Provjerava da li je regularan potez i odigrava potex
      * @param sourceFigure
      * @param destinationFigure
      * @return 
      */
    public Boolean move(Figure sourceFigure, Figure destinationFigure) {

        int fieldDistanceY = destinationFigure.getPositionOnFieldY() - sourceFigure.getPositionOnFieldY();
        int fieldDistanceX = destinationFigure.getPositionOnFieldX() - sourceFigure.getPositionOnFieldX();
        int positionY = sourceFigure.getPositionOnFieldY();



         //Provjera regularnosti poteza
        if ((fieldDistanceX == 0 && (fieldDistanceY == 1 || fieldDistanceY == -1 || fieldDistanceY == 7 || fieldDistanceY == -7)) || (fieldDistanceY == 0 && (positionY % 2) == 1 && (fieldDistanceX == 1 || fieldDistanceX == -1)) || !sourceFigure.isOnField() || numberOfFigureOfActivPlayer() <= 3) {

            if (sourceFigure.getPositionOnFieldX() != -1) {
                playerFigureMatrix[sourceFigure.getPositionOnFieldX()][sourceFigure.getPositionOnFieldY()] = null;
                ((Figure) fieldMatrix[sourceFigure.getPositionOnFieldX()][sourceFigure.getPositionOnFieldY()]).setVisible(true);

            }

            sourceFigure.setOnField(true);
            //Postavljanje figure na odgovarajucu poziciju u matrici
            playerFigureMatrix[destinationFigure.getPositionOnFieldX()][destinationFigure.getPositionOnFieldY()] = sourceFigure;

            
            //Pokretanje vizuelnog efekta pomjeranja figure na ploci
            //realizovano u drugom threadu
            PlayMove mov = new PlayMove(this.clicedFigure, destinationFigure);
            mov.thread.start();
            
            sourceFigure.setPositionOnFieldX(destinationFigure.getPositionOnFieldX());
            sourceFigure.setPositionOnFieldY(destinationFigure.getPositionOnFieldY());


            /**
             * Provjera da li su 3 u nizu
             * ako jesu igrac bira koju figuru ce da ukloni protivniku
             */
            if (chekStatus(sourceFigure)) {
                addEventForRemoveEnemyFigure();
                if (playerOnMove == 1) {

                    board.setMessage("Izaberite crvenu fuguru");
                } else {

                    board.setMessage("Izaberite plavu fuguru");
                }
            } else {
                /**
                 * Nakon svakog poteza se provjerava da li je neki igrac pobjedio
                 * ukoliko nema pobjednika igra se nastavlja
                 */
                if (chackWin()) {
                    removeAllFigureEvent();
                } else {
                    if (playerOnMove == 1) {
                        playerOnMove = 2;
                        board.setMessage("Crveni igrač je na potezu");
                    } else {
                        playerOnMove = 1;
                        board.setMessage("Plavi igrač je na potezu");
                    }

                    addEvent();
                }
            }
            return true;

        }
        return false;
    }
    /**
     * Provjera da li je igrac nakon odigranog poteza
     * napravio niz od 3 figure
     * @param f Figura igraca
     * @return 
     */
    private Boolean chekStatus(Figure f) {

        FigureType type;
       
            type = f.getType();
        int x=f.getPositionOnFieldX();
        int y=f.getPositionOnFieldY();

     

        if (y % 2 == 0) {
            if (playerFigureMatrix[x][y] != null && playerFigureMatrix[x][(y + 1) % 8] != null && playerFigureMatrix[x][(y + 2) % 8] != null) {
                if (((Figure) playerFigureMatrix[x][y]).getType() == type
                        && ((Figure) playerFigureMatrix[x][(y + 1) % 8]).getType() == type
                        && ((Figure) playerFigureMatrix[x][(y + 2) % 8]).getType() == type) {
                    return true;
                }

            }

            if (y == 0) {
                if (playerFigureMatrix[x][y] != null && playerFigureMatrix[x][7] != null && playerFigureMatrix[x][6] != null) {
                    if (((Figure) playerFigureMatrix[x][y]).getType() == type
                            && ((Figure) playerFigureMatrix[x][7]).getType() == type
                            && ((Figure) playerFigureMatrix[x][6]).getType() == type) {
                        return true;
                    }

                }
            } else {

                if (playerFigureMatrix[x][y] != null && playerFigureMatrix[x][y - 1] != null && playerFigureMatrix[x][y - 2] != null) {
                    if (((Figure) playerFigureMatrix[x][y]).getType() == type
                            && ((Figure) playerFigureMatrix[x][y - 1]).getType() == type
                            && ((Figure) playerFigureMatrix[x][y - 2]).getType() == type) {
                        return true;
                    }

                }

            }

        } else {
            if (playerFigureMatrix[0][y] != null && playerFigureMatrix[1][y] != null && playerFigureMatrix[2][y] != null) {
                if (((Figure) playerFigureMatrix[0][y]).getType() == type
                        && ((Figure) playerFigureMatrix[1][y]).getType() == type
                        && ((Figure) playerFigureMatrix[2][y]).getType() == type) {
                    return true;
                }
            }
            if (playerFigureMatrix[x][y] != null && playerFigureMatrix[x][(y + 1) % 8] != null && playerFigureMatrix[x][y - 1] != null) {
                if (((Figure) playerFigureMatrix[x][y]).getType() == type
                        && ((Figure) playerFigureMatrix[x][(y + 1) % 8]).getType() == type
                        && ((Figure) playerFigureMatrix[x][y - 1]).getType() == type) {
                    return true;
                }
            }

        }

        return false;

    }
}
