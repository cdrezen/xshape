package xshape.Model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.text.Position;

public class Rectangle extends ShapeAbstact
{
    private final int SELECTION_MARGIN = 3;

    public Rectangle(Point position, Dimension size) {
        super(position, size);
        name = "Rectangle";
        //TODO Auto-generated constructor stub
    }

    public Rectangle(int x, int y, int width, int height) {
        this(new Point(x, y), new Dimension(width, height));
        name = "Rectangle";
    }

    @Override
    public boolean contains(int X, int Y)
    {
        //cf java.awt.Rectangle

        int w = this.size.width;
        int h = this.size.height;
        if ((w | h) < 0) {
            // At least one of the dimensions is negative...
            return false;
        }
        // Note: if either dimension is zero, tests below must return false...
        int x = this.position.x;
        int y = this.position.y;
        if (X < x || Y < y) {
            return false;
        }
        w += x;
        h += y;
        //    overflow || intersect
        return ((w < x || w > X) &&
                (h < y || h > Y));
    }

    @Override
    public void drawAt(Graphics g, int x, int y) 
    {
        g.fillRect(x, y, size.width, size.height);
    }

    @Override
    public void drawSelection(Graphics g, int margin)
    {
        g.drawRect(position.x - margin,
        position.y - margin,        
        size.width + margin + margin,
        size.height + margin + margin);
    }

    @Override
    public Shape clone()
    {
        System.err.println("rect clone");
        return new Rectangle(new Point(position), new Dimension(size));
    }
}
