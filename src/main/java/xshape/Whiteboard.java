package xshape;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import xshape.DragDrop.ShapeTransferHandler;

public class Whiteboard extends JPanel
{
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    Shape selectedShape = null;
    boolean modifierKeyIsDown = false;

    public Whiteboard() {
        this.addMouseListener(mousePressAdapter);
        this.addMouseMotionListener(mouseDragAdapter);

        this.setTransferHandler(new ShapeTransferHandler());
        _factory = new ShapeFactory();
        createScene();
    }

    MouseAdapter mousePressAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) {
            selectedShape = null;

            for (Shape shape : _shapes) {
                if (shape.isIn(e.getX(), e.getY())) {
                    selectedShape = shape;
                    System.out.println("selected shape at:" + e.getX() + " " + e.getY());
                    break;
                }
            }

            if (selectedShape == null)
                System.out.println("unselected at:" + e.getX() + " " + e.getY());

            ((Whiteboard) e.getSource()).repaint();
        }
    };

    MouseAdapter mouseDragAdapter = new MouseAdapter() {

        @Override
        public void mouseDragged(MouseEvent e) 
        {
            if (selectedShape == null) return;
            Shape shape = selectedShape;
            
            shape.setPos(e.getX(), e.getY());
            System.out.println("drag to: " + e.getX() + " " + e.getY());

            Whiteboard canvas = (Whiteboard)e.getSource();

            if(e.isControlDown())
            {
                TransferHandler handle = canvas.getTransferHandler();
                handle.exportAsDrag(canvas, e, TransferHandler.COPY);
            }
            
            canvas.repaint();
        }

    };

    private void createScene() {
        Shape shape = _factory.createRectangle(250, 250, 75, 25);
        Shape shape1 = _factory.createCircle(235, 230, 30, 30);
        Shape shape2 = _factory.createCircle(260, 230, 30, 30);
        Shape shape3 = _factory.createCircle(250, 310, 25, 25);

        //shape.translate(new Point(100, 50));

        Shape[] tmp = { shape, shape1, shape2, shape3 };
        _shapes = tmp;
    }

    public void addShape(Shape shape)
    {
        Shape[] copy = _shapes.clone();
        _shapes = new Shape[copy.length + 1];

        for (int i = 0; i < copy.length; i ++) {
            _shapes[i] = copy[i];
        }

        Point point = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(point, this);
        shape.setPos(point.x, point.y);

        _shapes[_shapes.length - 1] = shape;

        System.out.println("new shape dropped.");

        this.repaint();
    }

    public Shape getSelection()
    {
        return selectedShape;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Shape s : _shapes)
            s.draw(g);

        if (selectedShape != null) selectedShape.drawSelectionRect(g);
    }
}
