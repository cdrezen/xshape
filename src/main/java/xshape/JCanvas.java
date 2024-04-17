package xshape;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class JCanvas extends JPanel
{
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;

    public JCanvas() {
        _factory = new ShapeFactory();
        createScene();
    }

    private void createScene() {
        Shape shape = _factory.createRectangle(100, 100, 50, 50);
        Shape shape2 = _factory.createRectangle(250, 250, 75, 20);
        shape.translate(new Point2D.Double(100, 50));
        Shape[] tmp = { shape, shape2 };
        _shapes = tmp;
    }

    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        AwtContext.instance().graphics(g);
        
        for (Shape s : _shapes)
            s.draw();
    }
}
