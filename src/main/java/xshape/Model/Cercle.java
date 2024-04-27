package xshape.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Cercle extends ShapeAbstact {

    public Cercle(Point position, Dimension size) {
        super(position, size);
        this.margin = 3;
        //TODO Auto-generated constructor stub
    }

    public Cercle(int x, int y, int width, int height) {
        this(new Point(x, y), new Dimension(width, height));
    }

    @Override
    public void drawAt(Graphics g, int x, int y) 
    {
        g.fillOval(x, y, size.width, size.height);
    }

    @Override
    public void drawSelection(Graphics g, int margin)
    {
        final int margin2 = 2 * margin;
        g.drawOval(position.x - margin, 
                    position.y - margin, 
                    size.width + margin2, 
                    size.height + margin2);
    }

    @Override
    public boolean contains(int x, int y) {
        //TODO inventer la roue
        return new Rectangle(position, size).contains(x, y);
    }
    
    @Override
    public Cercle clone(){
        return new Cercle(new Point(position), new Dimension(size));
    }
}
