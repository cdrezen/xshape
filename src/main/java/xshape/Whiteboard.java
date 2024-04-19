package xshape;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Whiteboard extends Canvas {
    private ShapeFactory _factory = null;
    Rectangle[] _shapes = null;
    Rectangle selectedRect = null;

    public Whiteboard() {
        this.addMouseListener(mousePressAdapter);
        this.addMouseMotionListener(mouseDragAdapter);
        _factory = new ShapeFactory();
        createScene();
    }

    MouseAdapter mousePressAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            selectedRect = null;

            for (Rectangle rectangle : _shapes) {
                if (rectangle.isIn(e.getX(), e.getY())) {
                    selectedRect = rectangle;
                    System.out.println("selected rect at:" + e.getX() + " " + e.getY());
                    break;
                }
            }

            if (selectedRect == null)
                System.out.println("unselected at:" + e.getX() + " " + e.getY());

            ((Whiteboard) e.getSource()).repaint();
        }
    };

    MouseAdapter mouseDragAdapter = new MouseAdapter() {

        @Override
        public void mouseDragged(MouseEvent e) 
        {
            if (selectedRect == null) return;
            Rectangle rect = selectedRect;// (Rectangle) e.getSource();

            rect.setPos(e.getX(), e.getY());
            System.out.println("drag to: " + e.getX() + " " + e.getY());
            ((Whiteboard) e.getSource()).repaint();
        }

    };

    private void createScene() {
        Rectangle shape = _factory.createRectangle(100, 100, 50, 50);
        Rectangle shape2 = _factory.createRectangle(250, 250, 75, 20);

        shape.translate(new Point(100, 50));

        Rectangle[] tmp = { shape, shape2 };
        _shapes = tmp;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Rectangle s : _shapes)
            s.draw(g);

        if (selectedRect != null) selectedRect.drawSelectionRect(g);
    }
}
