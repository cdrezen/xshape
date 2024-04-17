package xshape;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class Rectangle extends Canvas implements Shape 
{
    private Point pos = new Point(0, 0);
    private Dimension sz = new Dimension(0, 0);

    public Rectangle(int posX, int posY, int height, int width) 
    {
        super();
        this.setBounds(posX, posY, width, height);
        this.setMaximumSize(this.getSize());
        this.setMinimumSize(this.getSize());
        pos = new Point(posX, posY);
        sz = new Dimension(width, height);
	}

    @Override
    public Shape translate(Point vec) {
        this.setLocation(this.getX() + vec.x, this.getY() + vec.y);
        return this;
    }

    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        
        Color c = g.getColor();
        g.setColor(Color.yellow);

        g.fillRect(pos.x,
                    pos.y,        
                    sz.width,
                    sz.height);

        g.setColor(c);
    }

    
}
