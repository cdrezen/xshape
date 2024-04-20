package xshape;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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

        Whiteboard c = new Whiteboard();
        c.setBackground(Color.WHITE);
        c.setPreferredSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout());
        //frame.getContentPane().add(c);
        frame.getContentPane().add(c, BorderLayout.CENTER);
        frame.add(new ShapeToolBar(), BorderLayout.WEST);
        frame.add(new MenuBar(), BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }
}
