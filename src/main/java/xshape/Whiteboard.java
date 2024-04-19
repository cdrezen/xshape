package xshape;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class Whiteboard extends JPanel// implements DropTargetListener
{
    private ShapeFactory _factory = null;
    Shape[] _shapes = null;
    Shape selectedShape = null;
    Icon icontest = null;

    public Whiteboard() {
        this.addMouseListener(mousePressAdapter);
        this.addMouseMotionListener(mouseDragAdapter);

        this.setTransferHandler(new TransferHandler("icon"));
        //DropTarget dt = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
        //this.setDropTarget( dt );

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
            ((Whiteboard)e.getSource()).repaint();
        }

    };

    private void createScene() {
        Shape shape = _factory.createRectangle(100, 100, 50, 50);
        Shape shape1 = _factory.createRectangle(250, 250, 75, 20);

        shape.translate(new Point(100, 50));

        Shape[] tmp = { shape, shape1 };
        _shapes = tmp;
    }

    // @Override
    // public void drop(DropTargetDropEvent dtde) 
    // {
    //     System.out.println(dtde.getSource());
    //     //throw new UnsupportedOperationException("Unimplemented method 'drop'");
    //     try {           
    //         //Icon shapeIcon  = (Icon) dtde.getTransferable();
    //         //icontest = shapeIcon;
    //         dtde.dropComplete( true );
    //         repaint();
    //     } 
    //     catch( Exception e ) {
    //         e.printStackTrace();
    //     }
    // }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Shape s : _shapes)
            s.draw(g);

        if (selectedShape != null) selectedShape.drawSelectionRect(g);
        if(icontest != null) icontest.paintIcon(this, g, 100, 100);
    }
}
