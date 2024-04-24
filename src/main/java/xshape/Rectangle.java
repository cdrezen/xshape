package xshape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle implements Shape 
{
    private Point pos = new Point(0, 0);
    private Dimension sz = new Dimension(0, 0);
    //private boolean selected = false;

    public Rectangle(int posX, int posY, int height, int width) 
    {
        //super();
        pos = new Point(posX, posY);
        sz = new Dimension(width, height);
	}

    @Override
    public Shape translate(Point vec) {
        setPos(pos.x + vec.x, pos.y + vec.y);
        return this;
    }

    @Override
    public Point getPos() {
        return pos;
    }

    public void setPos(int posX, int posY)
    {
        pos.x = posX;
        pos.y = posY;
    }

    @Override
    public Dimension getSize() {
        return sz;
    }

    @Override
    public void setSize(int width, int height) {
        sz.width = width;
        sz.height = height;
    }

    public boolean isIn(int x, int y)
    {
        java.awt.Rectangle r = new java.awt.Rectangle(pos, sz);
        return r.contains(x, y);
    }

    // public void setSelected(boolean value)
    // {
    //     selected = value;
    // }

    public void draw(Graphics g) 
    {
        drawAt(g, pos.x, pos.y);
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
        Color c = g.getColor();
        g.setColor(Color.magenta);

        final int margin = 3;

        g.drawRect(pos.x - margin,
        pos.y - margin,        
        sz.width + margin + margin,
        sz.height + margin + margin);
        
        g.setColor(c);
    }

    public Shape clone()
    {
        return new Rectangle(pos.x, pos.y, sz.width, sz.height);
    }

    // @Override
    // public void add(Shape s) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'add'");
    // }

    // @Override
    // public PrototypeShape translateP(Point vec) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'translateP'");
    // }
}
