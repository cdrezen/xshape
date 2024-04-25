package xshape;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import xshape.Model.Shape;

public class ShapeIcon implements Icon {

    private Shape shape;

    public ShapeIcon(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        shape.drawAt(g, x, y);
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
