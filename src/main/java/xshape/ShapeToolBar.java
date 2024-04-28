package xshape;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.TransferHandler;

import xshape.DragDrop.DeleteShapeTransferHandler;
import xshape.DragDrop.ShapeTransferHandler;
import xshape.Model.Shape;
import xshape.Model.ShapeFactory;

public class ShapeToolBar extends JToolBar
{
    ArrayList<JButton> buttons;

    public ShapeToolBar()
    {
        super();
        this.setOrientation(VERTICAL);


        this.setLayout(new GridLayout(8, 1));

        ShapeFactory shapeFactory = new ShapeFactory();

        buttons = new ArrayList<>();

        //bouttons avec shape comme icone
        this.add(shapeFactory.createCircle(0, 0, 50, 50));
        this.add(shapeFactory.createRectangle(0, 0, 50, 50));
        this.add(shapeFactory.createPolygon(0, 0, 5, 30));



        JButton delButton = buildResButton("Delete.png", null);
        delButton.setTransferHandler(new DeleteShapeTransferHandler());
        this.add(delButton);


        //this.add(buildResButton("Composition.png", null), 1);

        this.setTransferHandler(new ShapeTransferHandler());
    }

    //ajouter un Shape comme boutton
    public void add(Shape shape)
    {
        System.out.println("added button");
        JButton btn = buildShapeButton(shape);
        buttons.add(btn);
        this.add(btn, this.getComponentCount() - 1);//poubelle doit toujours etre en bas
    }

    public void remove(Shape shape)
    {
        JButton foundBtn = null;
        for (JButton btn : buttons) 
        {
            if(((ShapeIcon)btn.getIcon()).getShape() == shape)
            {
                foundBtn =btn;
                break;
            }
        }

        if(foundBtn == null) return;

        System.out.println("found btn");

        this.remove(foundBtn);
        buttons.remove(foundBtn);
        this.revalidate();
        this.repaint();
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