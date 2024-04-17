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

        JCanvas jc = new JCanvas();
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        frame.getContentPane().add(jc);
        frame.add(new AwtToolBar(), BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
    }

}
