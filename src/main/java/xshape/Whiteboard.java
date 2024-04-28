package xshape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    ContextMenu menu;
    private ShapeFactory _factory = null;
    ShapeGroup _shapes = null;
    ShapeGroup _selectedShapes = null;
    //Shape selectedShape = null;
    Rectangle selectionRect = null;
    Point mousePressPt;

    public Whiteboard() {
        menu = new ContextMenu(this);
        this.addMouseListener(mousePressAdapter);
        this.addMouseMotionListener(mouseDragAdapter);

        this.setTransferHandler(new ShapeTransferHandler());
        _factory = new ShapeFactory();
        createScene();
    }

    public ArrayList<Shape> getShapeGroup(){
        return _shapes.getShapeGroup();
    }

    MouseAdapter mousePressAdapter = new MouseAdapter() {

        @Override
        public void mousePressed(MouseEvent e) 
        {
            if(e.isPopupTrigger()) doPop(e);
            if(menu.isVisible()) return;

            mousePressPt = e.getPoint();

            if (_selectedShapes == null)
                _selectedShapes = _shapes.getShapesAt(e.getX(), e.getY());//to set pos dragging

            System.out.println("press: " + ((_selectedShapes == null) ? "un" : "") + "selected at:" + e.getX() + " " + e.getY());

            ((Whiteboard) e.getSource()).repaint();
        }
        
        @Override
        public void mouseReleased(MouseEvent e) 
        {
            if(e.isPopupTrigger()) doPop(e);
            if(menu.isVisible()) return;

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

    private void doPop(MouseEvent e) {
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

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

            _selectedShapes.setCenterToPos(e.getX(), e.getY());
            //_selectedShapes.setPos(e.getX(), e.getY());
            
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
        _shapes.add(_factory.createPolygon(150, 150, 5, 20));
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

    public void removeAllShape()
    {
        _shapes = new ShapeGroup();
        System.out.println("All shape deletion.");
        clearCanvas();
        this.repaint();
    }

    public void clearCanvas() {
        Graphics g = getGraphics();  // Get the graphics context
        g.setColor(getBackground());  // Set color to background color
        g.clearRect(0, 0, getWidth(), getHeight());  // Clear the entire canvas
    }

    public void addShapeFromJson(Shape shape)
    {
        _shapes.add(shape);
        System.out.println("new shape dropped.");
        this.repaint();
    }

    public ShapeGroup getSelection()
    {
        return _selectedShapes;
    }

    public ShapeGroup getShapes()
    {
        return _shapes;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // for (Shape s : _shapes)
        //     s.draw(g);

        _shapes.draw(g);

        if(_selectedShapes != null) _selectedShapes.drawSelection(g, true, Color.green, 0);
        if (selectionRect != null) selectionRect.drawSelection(g, false, Color.magenta, 0);
    }
   
}
