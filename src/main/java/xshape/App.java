package xshape;

import java.awt.*;
import javax.swing.*;

import xshape.DragDrop.ShapeTransferHandler;

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

        ActionListener editListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Shape selectedShape = canvas.getSelection();
                if(selectedShape != null)
                {
                    EditPanel edit = new EditPanel(selectedShape, canvas);
                    int choice = JOptionPane.showConfirmDialog(frame,
                                    edit,
                                    "Edit shape",
                                    JOptionPane.OK_CANCEL_OPTION);

                    if(choice == JOptionPane.OK_OPTION)
                    {
                        int[] res = edit.result();
                        //edit shape with result
                    }

                }
            }
            
        };

        MenuBar menu = new MenuBar(editListener);
        frame.add(menu, BorderLayout.NORTH);

        frame.pack();
        frame.setVisible(true);
    }
}
