package xshape;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.TransferHandler;
import javax.swing.UIManager;

import xshape.DragDrop.ShapeTransferHandler;

public class ShapeToolBar extends JToolBar
{
    public ShapeToolBar()
    {
        super();
        this.setOrientation(VERTICAL);


        this.setLayout(new GridLayout(8, 1));

        ShapeFactory shapeFactory = new ShapeFactory();

        //bouttons avec shape comme icone
        this.add(shapeFactory.createRectangle(4, 4, 50, 50));
        this.add(shapeFactory.createCircle(20, 20, 50, 50));

        JButton delButton = buildResButton("Delete.png", null);
        this.add(delButton);

        this.add(buildResButton("Composition.png", null), 1);

        this.setTransferHandler(new ShapeTransferHandler());
    }

    //ajouter un Shape comme boutton
    public void add(Shape shape)
    {
        System.out.println("added button");
        JButton btn = buildShapeButton(shape);
        this.add(btn);
    }

    private JButton buildShapeButton(Shape shape)
    {
        final MouseAdapter mouseDragAdapter = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                TransferHandler handle = button.getTransferHandler();
                handle.exportAsDrag(button, e, TransferHandler.COPY);
            }
        };

        JButton btn = new JButton(new ShapeIcon(shape));

        //dragndrop
        btn.setTransferHandler(new ShapeTransferHandler());
        btn.addMouseMotionListener(mouseDragAdapter);

        return btn;
    }

    private JButton buildResButton(String filename, ActionListener actionListener)
    {
        ImageIcon icon = null;

        try 
        {
            icon = ResourceHelper.getIconResource(filename);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        JButton button = new JButton(icon);
        if(actionListener != null) button.addActionListener(actionListener);

        return button;
    }
}