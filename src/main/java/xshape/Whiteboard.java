package xshape;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

import xshape.DragDrop.ShapeTransferHandler;
import xshape.Model.Rectangle;
import xshape.Model.Shape;
import xshape.Model.ShapeFactory;
import xshape.Model.ShapeGroup;

public class Whiteboard extends JPanel
{
    private ShapeFactory _factory = null;
    ShapeGroup _shapes = null;
    ShapeGroup _selectedShapes = null;
    //Shape selectedShape = null;
    Rectangle selectionRect = null;
    Point mousePressPt;

    public Whiteboard() {
        this.addMouseListener(mousePressAdapter);
        this.addMouseMotionListener(mouseDragAdapter);

        this.setTransferHandler(new ShapeTransferHandler());
        _factory = new ShapeFactory();
        createScene();
    }

    MouseAdapter mousePressAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) 
        {
            mousePressPt = e.getPoint();

            if (_selectedShapes == null)
                _selectedShapes = _shapes.getShapesAt(e.getX(), e.getY());//to set pos dragging

            System.out.println("press: " + ((_selectedShapes == null) ? "un" : "") + "selected at:" + e.getX() + " " + e.getY());

            ((Whiteboard) e.getSource()).repaint();
        }
        
        @Override
        public void mouseReleased(MouseEvent e) 
        {
            if(selectionRect == null) 
            {
                _selectedShapes = _shapes.getShapesAt(e.getX(), e.getY());//unselect/select 
                //_selectedShapes = null;
            }
            else
            {
                _selectedShapes = _shapes.getShapesIn(selectionRect);
                selectionRect = null;
            }

            System.out.println("release: " + ((_selectedShapes == null) ? "un" : "") + "selected");

            ((Whiteboard) e.getSource()).repaint();
        };
    };

    MouseAdapter mouseDragAdapter = new MouseAdapter() {

        @Override
        public void mouseDragged(MouseEvent e) 
        {
            Whiteboard canvas = (Whiteboard)e.getSource();

            if (_selectedShapes == null)
            {
                selectionRect = _factory.createRectangle(Math.min(e.getX(), mousePressPt.x), 
                                                        Math.min(e.getY(), mousePressPt.y),
                                                        Math.abs(e.getX() - mousePressPt.x),
                                                        Math.abs(e.getY() - mousePressPt.y));

                canvas.repaint();
                return;
            }

            if(e.isControlDown())
            {
                TransferHandler handle = canvas.getTransferHandler();
                handle.exportAsDrag(canvas, e, TransferHandler.COPY);
                return;
            }

            //if(!_selectedShapes.contains(e.getX(), e.getY())) return;//prevent drag from anywhere

            //_selectedShapes.setCenterToPos(e.getX(), e.getY());
            _selectedShapes.setPos(e.getX(), e.getY());
            
            System.out.println("drag to: " + e.getX() + " " + e.getY());
            
            canvas.repaint();
        }

    };

    private void createScene() {
        _shapes = new ShapeGroup();
        Shape shape = _factory.createRectangle(250, 250, 75, 25);
        // Shape shape1 = _factory.createCircle(235, 230, 30, 30);
        // Shape shape2 = _factory.createCircle(260, 230, 30, 30);
        // Shape shape3 = _factory.createCircle(250, 310, 25, 25);

        //shape.translate(new Point(100, 50));

        _shapes.add(shape);
    }

    public void addShape(Shape shape)
    {
        Point point = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(point, this);
        shape.setPos(point.x, point.y);


        _shapes.add(shape);
        System.out.println("new shape dropped.");
        this.repaint();
    }

    public void removeShape(Shape shape)
    {
        _shapes.remove(shape);
        System.out.println("shape deletion.");
        this.repaint();
    }

    public Shape getSelection()
    {
        return _selectedShapes;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // for (Shape s : _shapes)
        //     s.draw(g);

        _shapes.draw(g);

        if(_selectedShapes != null) _selectedShapes.drawSelection(g);
        if (selectionRect != null) selectionRect.drawSelection(g);
    }
}
