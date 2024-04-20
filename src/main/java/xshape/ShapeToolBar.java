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

import xshape.DragDrop.ShapeIconTransferHandler;

import javax.swing.JSlider;

public class ShapeToolBar extends JToolBar 
{
    public ShapeToolBar()
    {
        super();
        this.setOrientation(VERTICAL);

        MouseAdapter mouseDragAdapter = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                TransferHandler handle = button.getTransferHandler();
                handle.exportAsDrag(button, e, TransferHandler.COPY);
            }
        };

        this.setLayout(new GridLayout(8, 1));
        //shape comme icone
        Rectangle shape = new ShapeFactory().createRectangle(4, 4, 50, 50);
        JButton rectBtn = new JButton(new ShapeIcon(shape));

        //dragndrop
        rectBtn.setTransferHandler(new ShapeIconTransferHandler());
        rectBtn.addMouseMotionListener(mouseDragAdapter);

        this.add(rectBtn);

        JButton delButton = createResButton("Delete.png", null);
        this.add(delButton);

        this.add(createResButton("Composition.png", null), 1);

        //this.add(new JButton(UIManager.getIcon("FileView.directoryIcon")));

        // JSlider szSlider = new JSlider(JSlider.HORIZONTAL,0,50,10);
        // szSlider.setSize(rectBtn.getSize());
        // this.add(szSlider);
    }

    private JButton createResButton(String filename, ActionListener actionListener)
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