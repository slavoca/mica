/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;

/**
 *
 * @author Slavica
 */
public class Figure extends JButton {
    //kordinate na polju

    private int x;
    private int y;
    //da li je aktivirana odredjena figura
    private Boolean presed = false;
    //tip figure
    private FigureType type;
    //dimenzije figure
    private final int w = 40;
    private final int h = 40;
    private int width;
    private int height;
    private Board board;
    private Game game;
    //boja figure
    private Color color;
    //boja aktivirane figure
    private Color presedColor;
    //da li je figura na terenu
    private Boolean onField = false;
    //da li je figura ziva tj da li je izbacena iz igre (pojedena)
    private Boolean alive = true;
    //Pozicija na polju
    private int positionOnFieldX;
    private int positionOnFieldY;

    public Boolean isAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Game getGame() {
        return game;
    }

    public void setPositionOnFieldX(int positionOnFieldX) {
        this.positionOnFieldX = positionOnFieldX;
    }

    public void setPositionOnFieldY(int positionOnFieldY) {
        this.positionOnFieldY = positionOnFieldY;
    }

    public int getPositionOnFieldX() {
        return positionOnFieldX;
    }

    public int getPositionOnFieldY() {
        return positionOnFieldY;
    }

    public Boolean isOnField() {
        return onField;
    }

    public void setOnField(Boolean onField) {
        this.onField = onField;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(FigureType type) {
        this.type = type;
    }

    public FigureType getType() {
        return type;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public Boolean isPresed() {
        return presed;
    }

    public void setPresed(Boolean presed) {
        this.presed = presed;
    }

    /**
     * Kreiranje nove figure
     *
     * @param game
     * @param x
     * @param y
     * @param color
     * @param presedColor
     * @param type
     * @param positionOnFieldX
     * @param positionOnFieldY
     */
    public Figure(Game game, int x, int y, Color color, Color presedColor, FigureType type, int positionOnFieldX, int positionOnFieldY) {
        super("");
        this.color = color;
        this.game = game;
        this.board = game.getBoard();

        if (type == FigureType.EMPTY_FIELD) {
            this.width = 30;
            this.height = 30;
        } else {
            this.width = w;
            this.height = h;
        }


        this.x = x - (this.width / 2);
        this.y = y - (this.height / 2);

        this.type = type;
        this.presedColor = presedColor;
        this.positionOnFieldX = positionOnFieldX;
        this.positionOnFieldY = positionOnFieldY;


        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setBounds(this.x, this.y, width, height);
        setContentAreaFilled(false);
        setVisible(true);
        this.board.add(this);

    }

    /**
     * Postavljanje figure na poziciju na polju
     */
    public void setPosition() {
        setBounds(x, y, width, height);
    }

    /**
     * Bojenje figure u zavisnosti od stanja
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        if (presed) {
            g.setColor(this.presedColor);
        } else {
            g.setColor(this.color);
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
    Shape shape;

    @Override
    public boolean contains(int x, int y) {
        if (shape == null
                || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
