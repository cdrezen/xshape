package xshape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

public class CerclePrototype implements PrototypeShape {
    private Dimension sz = new Dimension(0, 0);
    private double x,  y,  w,  h;

    public CerclePrototype(int posX, int posY, int height, int width) 
    {
        //super();
        x = posX;
        y = posY;
        w = width;
        h = height;
        sz = new Dimension(width, height);
	}

    @Override
    public PrototypeShape clone(){
        return this.clone();
        // return new CerclePrototype((int)x,(int)y,(int)w,(int)h);
    }


    @Override
    public void setPos(int posX, int posY) {
        x = posX;
        y = posY;
    }

    @Override
    public Dimension getSize() {
        return sz;
    }

    @Override
    public boolean isIn(int x, int y) {
        Ellipse2D c = new Ellipse2D.Double(this.x,  this.y,  w,  h);
        return c.contains(x, y);
    }

    @Override
    public void draw(Graphics g) {
        drawAt(g, (int)x, (int)y);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        Color c = g.getColor();
        g.setColor(Color.yellow);

        g.fillRect(x, y, sz.width, sz.height);

        g.setColor(c);
    }

    public void drawSelectionRect(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Color c = g.getColor();
        g.setColor(Color.magenta);

        final int margin = 3;
        final int margin2 = 2 * margin;
        Ellipse2D ellipse = new Ellipse2D.Double(5 - margin, 10 - margin, 100 - margin2, - margin2);
        g2.draw(ellipse);
        
        g.setColor(c);
    }

    @Override
    public PrototypeShape translate(Point vec) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'translate'");
    }
    
}
