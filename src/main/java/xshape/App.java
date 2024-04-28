package xshape;

import java.awt.*;
import javax.swing.*;

import xshape.Model.Polygon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


public class App {

    public static void main(String[] args) 
    {
        JFrame frame = new JFrame("XShape Swing/AWT Rendering");
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(wa);

        Whiteboard canvas = new Whiteboard();
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(canvas, BorderLayout.CENTER);

        ShapeToolBar toolBar = new ShapeToolBar();
        frame.add(toolBar, BorderLayout.WEST);

        MenuBar menu = new MenuBar(canvas);
        frame.add(menu, BorderLayout.NORTH);

        setIcon(frame);
        frame.pack();
        frame.setVisible(true);
    }

    static void setIcon(Frame frame)
    {
        final Polygon hex = new Polygon(new Point(0, 0), 5, 32);
        final Taskbar taskbar = Taskbar.getTaskbar();
        try {
            BufferedImage bi = new BufferedImage((int)hex.size.width, (int)hex.size.height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D)bi.getGraphics();
            hex.draw(g2d);

            frame.setIconImage(bi);
            taskbar.setIconImage(bi);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
