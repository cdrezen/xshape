package xshape;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import xshape.Model.Shape;
import xshape.Model.ShapeAbstact;

public class ShapeIcon implements Icon {

    private Shape shape;

    public ShapeIcon(Shape shape) {
        this.shape = shape.clone();
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        shape.setPos(x, y);
        shape.draw(g);
    }

    @Override
    public int getIconWidth() {
        return shape.size().width;
    }

    @Override
    public int getIconHeight() {
        return shape.size().height;
    }

    public Shape getShape()
    {
        return shape;
    }
}
