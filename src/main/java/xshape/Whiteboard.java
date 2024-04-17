package xshape;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Whiteboard extends JPanel
{
    private ShapeFactory _factory = null;
    Rectangle[] _shapes = null;

    public Whiteboard() {
        _factory = new ShapeFactory();
        createScene();
    }

    MouseAdapter mouseDragAdapter = new MouseAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            Rectangle rect = (Rectangle) e.getSource();
            rect.translate(new Point(e.getX(), e.getY()));
		    System.out.println(e.getX() + " " + e.getY());
            rect.repaint();
        }
    };

    private void createScene() {
        this.setPreferredSize(new Dimension(500, 500));
        Rectangle shape = _factory.createRectangle(100, 100, 50, 50);
        shape.addMouseMotionListener(mouseDragAdapter);
        Rectangle shape2 = _factory.createRectangle(250, 250, 75, 20);
        //shape.translate(new Point(100, 50));
        //Rectangle[] tmp = { shape, shape2 };
        //_shapes = tmp;
        
        //JPanel extraPanel = new JPanel(new FlowLayout());
        shape.setPreferredSize(shape.getSize());
        //shape2.setPreferredSize(shape2.getSize());

        this.setLayout(new BorderLayout());
        //extraPanel.add(shape);
        this.add(shape);
        //this.add(shape2);
        // JButton b = new JButton();
        // b.addMouseMotionListener(mouseDragAdapter1);
        // this.add(b);
        this.revalidate();
    }

    // @Override
    // public void paint(Graphics g) 
    // {
    //     super.paint(g);
        
    //     for (Rectangle s : _shapes)
    //         s.paint(g);
    // }
}
