package xshape;

import java.awt.*;
import javax.swing.*;

import xshape.DragDrop.ShapeTransferHandler;
import xshape.Model.Shape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        Whiteboard canvas = new Whiteboard();
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(new Dimension(500, 500));
        frame.setLayout(new BorderLayout());
        frame.getContentPane().add(canvas, BorderLayout.CENTER);

        ShapeToolBar toolBar = new ShapeToolBar();
        frame.add(toolBar, BorderLayout.WEST);

        MenuBar menu = new MenuBar(canvas);
        frame.add(menu, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
    }
}
